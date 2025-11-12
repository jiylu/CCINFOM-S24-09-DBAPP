package controllers;

import dao.CategoriesDAO;
import dao.TechniciansDAO;
import dao.TicketsDAO;
import dao.UserDAO;
import db.DBConnection;
import models.Categories;
import models.Tickets;
import models.User;
import view.Frame;
import view.technician.CategoryItem;
import view.technician.ResolveTicketTechnicianPanel;
import view.technician.TechnicianDashboardPanel;
import view.technician.TechnicianTicketQueue;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TechnicianDashboardController {
    private User user;
    private Frame frame;
    private TechnicianDashboardPanel panel;
    private ResolveTicketTechnicianPanel resolveTicketTechnicianPanel;
    private TechnicianTicketQueue ticketQueuePanel;
    private TicketsDAO ticketsDAO;
    private CategoriesDAO categoriesDAO;
    private List<Integer> categoryIds;

    public TechnicianDashboardController(User user, Frame frame, TicketsDAO ticketsDAO, CategoriesDAO categoriesDAO){
        this.user = user;
        this.frame = frame;
        this.panel = frame.getTechnicianDashboardPanel();
        this.resolveTicketTechnicianPanel = panel.getResolveTicketTechnicianPanel();
        this.ticketQueuePanel = panel.getTechnicianTicketQueuePanel();
        this.ticketsDAO = ticketsDAO;
        this.categoriesDAO = categoriesDAO;
    }

    public void init(){
        frame.showPanel(Frame.TECHNICIAN_PANEL);
        initListeners();
        loadTicketCategories();
        loadAssignedTickets();
    }

    private void initListeners(){
        panel.getResolveTicketButton().addActionListener(e -> {
            loadTicketCategories();
            loadAssignedTickets();
            panel.showPanel(TechnicianDashboardPanel.RESOLVE_TICKET);
        });

        resolveTicketTechnicianPanel.getTicketsToResolve().addActionListener(e -> {
            updateTicketDetails();
        });

        resolveTicketTechnicianPanel.getSaveButton().addActionListener(e -> {
            saveTicketChanges();
        });

        panel.getViewTicketQueueButton().addActionListener(e -> {
            ticketQueuePanel.clearTickets();
            loadTicketQueue();
            panel.showPanel(TechnicianDashboardPanel.TICKET_QUEUE);
        });
    }

    private void loadTicketQueue() {
        try {
            int technicianId = new TechniciansDAO(DBConnection.connect())
                    .getTechnicianIdByUserId(user.getUserID());
            List<Tickets> tickets = ticketsDAO.getTicketsByTechninicianID(technicianId);

            for (Tickets t : tickets) {
                ticketQueuePanel.addTicket(
                    String.valueOf(t.getTicket_id()),
                    String.valueOf(t.getTicket_subject()),
                    String.valueOf(t.getCategory_id()),
                    String.valueOf(t.getEmployee_id()),
                    String.valueOf(t.getTechnician_id()),
                    t.getCreation_date(),
                    t.getStatus()
                );
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void loadAssignedTickets() {
        try {
            int technicianId = new TechniciansDAO(DBConnection.connect())
                    .getTechnicianIdByUserId(user.getUserID());
            List<Tickets> tickets = ticketsDAO.getTicketsByTechnician(technicianId);

            if (tickets == null || tickets.isEmpty()){
                resolveTicketTechnicianPanel.getTicketsToResolve()
                        .setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"No Tickets"}));
                return;
            }

            String[] ticketLabels = new String[tickets.size()];
            for(int i = 0; i < tickets.size(); i++){
                Tickets t = tickets.get(i);
                ticketLabels[i] = "Ticket#" + t.getTicket_id();
            }

            resolveTicketTechnicianPanel.getTicketsToResolve()
                    .setModel(new javax.swing.DefaultComboBoxModel<>(ticketLabels));

            resolveTicketTechnicianPanel.setTicketsList(tickets);

            // Select the first ticket
            resolveTicketTechnicianPanel.getTicketsToResolve().setSelectedIndex(0);
            updateTicketDetails();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateTicketDetails() {
        int index = resolveTicketTechnicianPanel.getTicketsToResolve().getSelectedIndex();
        if (index < 0) return;

        Tickets selected = resolveTicketTechnicianPanel.getTicketsList().get(index);

        resolveTicketTechnicianPanel.getTicketsSubjectLabel()
                .setText(selected.getTicket_subject());

        resolveTicketTechnicianPanel.getEmployeeIDLabel()
                .setText(String.valueOf(selected.getEmployee_id()));

        resolveTicketTechnicianPanel.getCreationDateLabel()
                .setText(selected.getCreation_date());

        resolveTicketTechnicianPanel.getStatus().setSelectedItem(selected.getStatus());

        for (int i = 0; i < categoryIds.size(); i++) {
            if (categoryIds.get(i) == selected.getCategory_id()) {
                resolveTicketTechnicianPanel.getCategories().setSelectedIndex(i);
                break;
            }
        }
    }

    private void loadTicketCategories() {
        try {
            List<Categories> categories = categoriesDAO.getAllCategories();
            JComboBox<CategoryItem> categoriesCombo = resolveTicketTechnicianPanel.getCategories();
            categoriesCombo.removeAllItems();

            categoryIds = new ArrayList<>();

            for (Categories c : categories) {
                categoriesCombo.addItem(new CategoryItem(c.getCategoryID(), c.getCategoryName()));
                categoryIds.add(c.getCategoryID());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Failed to load categories!");
        }
    }

    private void saveTicketChanges() {
        int selectedIndex = resolveTicketTechnicianPanel.getTicketsToResolve().getSelectedIndex();
        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(frame, "No ticket selected!");
            return;
        }

        Tickets selected = resolveTicketTechnicianPanel.getTicketsList().get(selectedIndex);

        // Status selected by tech
        String newStatus = (String) resolveTicketTechnicianPanel.getStatus().getSelectedItem();

        // Category combo selected
        CategoryItem selectedCategory =
                (CategoryItem) resolveTicketTechnicianPanel.getCategories().getSelectedItem();

        if (selectedCategory != null) {
            selected.setCategory_id(selectedCategory.getId());
        }

        if (!"Active".equals(newStatus)) {
            selected.setResolve_date(java.time.LocalDateTime.now().toString());
        }

        selected.setStatus(newStatus);

        try {
            boolean success = ticketsDAO.updateTicket(selected);
            if (success) {
                JOptionPane.showMessageDialog(frame,
                        "Ticket updated successfully!");

                if("Resolved".equalsIgnoreCase(newStatus)){
                    activateNextEnqueuedTicket();
                }

                loadAssignedTickets();
                panel.showPanel(TechnicianDashboardPanel.EMPTY_PANEL);
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Update failed. Please try again.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame,
                    "Error updating ticket!");
        }
    }

    private void activateNextEnqueuedTicket(){
        try {
            int technicianID = new TechniciansDAO(DBConnection.connect()).getTechnicianIdByUserId(user.getUserID());

            List<Tickets> enqueuedTickets = ticketsDAO.getEnqueuedTicketsByTechnician(technicianID);

            if (enqueuedTickets != null && !enqueuedTickets.isEmpty()){
                Tickets nextTicket = enqueuedTickets.get(0);

                nextTicket.setStatus("Active");
                nextTicket.setTechnician_id(technicianID);

                boolean activated = ticketsDAO.updateTicket(nextTicket);
                if (activated) {
                    JOptionPane.showMessageDialog(frame, "Next enqueued ticket (" + nextTicket.getTicket_id() + ") is now Active!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

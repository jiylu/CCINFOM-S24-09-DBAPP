package controllers;

import dao.CategoriesDAO;
import dao.TechniciansDAO;
import dao.TicketsDAO;
import dao.UserDAO;
import models.Categories;
import models.Tickets;
import models.User;
import view.Frame;
import view.technician.CategoryItem;
import view.technician.ResolveTicketTechnicianPanel;
import view.technician.TechnicianDashboardPanel;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TechnicianDashboardController {
    private User user;
    private Frame frame;
    private TechnicianDashboardPanel panel;
    private ResolveTicketTechnicianPanel resolveTicketTechnicianPanel;
    private TicketsDAO ticketsDAO;
    private CategoriesDAO categoriesDAO;
    private List<Integer> categoryIds;

    public TechnicianDashboardController(User user, Frame frame, TicketsDAO ticketsDAO, CategoriesDAO categoriesDAO){
        this.user = user;
        this.frame = frame;
        this.panel = frame.getTechnicianDashboardPanel();
        this.resolveTicketTechnicianPanel = panel.getResolveTicketTechnicianPanel();
        this.ticketsDAO = ticketsDAO;
        this.categoriesDAO = categoriesDAO;
    }

    public void init(){
        frame.showPanel(Frame.TECHNICIAN_PANEL);
        initListeners();
        loadAssignedTickets();
    }

    private void initListeners(){
        panel.getResolveTicketButton().addActionListener(e -> {
            loadAssignedTickets();
            loadTicketCategories();
            panel.showPanel(TechnicianDashboardPanel.RESOLVE_TICKET);
        });

        resolveTicketTechnicianPanel.getTicketsToResolve().addActionListener(e -> {
            updateTicketDetails();
        });

        resolveTicketTechnicianPanel.getSaveButton().addActionListener(e -> {
            saveTicketChanges();
        });
    }

    private void loadAssignedTickets() {
        try {
            int technicianId = user.getUserID();
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

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateTicketDetails() {
        int index = resolveTicketTechnicianPanel.getTicketsToResolve().getSelectedIndex();
        if (index < 0) return;

        Tickets selected = resolveTicketTechnicianPanel.getTicketsList().get(index);

        resolveTicketTechnicianPanel.getEmployeeIDLabel()
                .setText("Employee ID: " + selected.getEmployee_id());

        resolveTicketTechnicianPanel.getCreationDateLabel()
                .setText("Creation Date: " + selected.getCreation_date());

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

            for (Categories c : categories) {
                categoriesCombo.addItem(new CategoryItem(c.getCategoryID(), c.getCategoryName()));
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

                loadAssignedTickets(); // refresh list
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
}

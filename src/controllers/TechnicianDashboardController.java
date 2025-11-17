package controllers;

import dao.CategoriesDAO;
import dao.TechniciansDAO;
import dao.TicketsDAO;
import db.DBConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import models.Categories;
import models.TechUser;
import models.Tickets;
import view.Frame;
import view.technician.*;

public class TechnicianDashboardController {
    private TechUser user;
    private Frame frame;
    private TechnicianDashboardPanel panel;
    private ResolveTicketTechnicianPanel resolveTicketTechnicianPanel;
    private CancelTicketTechnicianPanel cancelTicketPanel;
    private TechnicianTicketQueue ticketQueuePanel;
    private TicketHistory ticketHistoryPanel;
    private TicketsDAO ticketsDAO;
    private TechniciansDAO techDAO;
    private CategoriesDAO categoriesDAO;
    private List<Integer> categoryIds;

    public TechnicianDashboardController(TechUser user, Frame frame, TicketsDAO ticketsDAO, TechniciansDAO techDAO, CategoriesDAO categoriesDAO){
        this.user = user;
        this.frame = frame;
        this.panel = frame.getTechnicianDashboardPanel();
        this.resolveTicketTechnicianPanel = panel.getResolveTicketTechnicianPanel();
        this.cancelTicketPanel = panel.getCancelTicketPanel();
        this.ticketHistoryPanel = panel.getTicketHistoryPanel();
        this.ticketQueuePanel = panel.getTechnicianTicketQueuePanel();
        this.ticketsDAO = ticketsDAO;
        this.techDAO = techDAO;
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

        panel.getTicketHistoryButton().addActionListener(e -> {
            ticketHistoryPanel.clearTickets();
            loadTicketHistory();
            panel.showPanel(TechnicianDashboardPanel.TICKET_HISTORY);
        });

        panel.getCancelTicketButton().addActionListener(e -> {
            loadActiveTicketForCancel();
            panel.showPanel(TechnicianDashboardPanel.CANCEL_TICKET);
        });
    }

    private void loadActiveTicketForCancel() {
    try {
        int technicianId = techDAO.getTechnicianIdByUserId(user.getUserID());
        Tickets activeTicket = ticketsDAO.getActiveTicketByTechnicianID(technicianId);

        if (activeTicket != null) {
            String details = String.format(
                    "Ticket ID: %d\nSubject: %s\nEmployee ID: %d\nCategory ID: %d\nCreated: %s\nStatus: %s\n\nDescription: %s",
                    activeTicket.getTicket_id(),
                    activeTicket.getTicket_subject(),
                    activeTicket.getEmployee_id(),
                    activeTicket.getCategory_id(),
                    activeTicket.getCreation_date(),
                    activeTicket.getStatus(),
                    activeTicket.getTicket_description()
            );

            cancelTicketPanel.setActiveTicket(details);

            // Attach logic for cancellation
            // Remove previous listeners to prevent duplicates
            for (var listener : cancelTicketPanel.getCancelTicketButton().getActionListeners()) {
                cancelTicketPanel.getCancelTicketButton().removeActionListener(listener);
            }

            // Add fresh listener
            cancelTicketPanel.getCancelTicketButton().addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you want to cancel this ticket?",
                        "Confirm Cancellation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    cancelActiveTicket(activeTicket);
                }
            });

        } else {
            cancelTicketPanel.setActiveTicket("No active ticket found.");
            cancelTicketPanel.getCancelTicketButton().setEnabled(false);
        }

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(frame, "Failed to load active ticket.");
    }
}

private void cancelActiveTicket(Tickets ticket) {
    try {
        ticket.setStatus("Cancelled");
        String reason = cancelTicketPanel.getCancelReason();
        String resolve_date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        boolean success = ticketsDAO.updateTicket(ticket);

        if (success) {
            ticketsDAO.insertToCancelledTickets(ticket.getTicket_id(), resolve_date, reason);
            activateNextEnqueuedTicket(); // activate next in queue
            panel.showPanel(TechnicianDashboardPanel.EMPTY_PANEL);
        } else {
            JOptionPane.showMessageDialog(frame, "Failed to cancel the ticket. Try again.");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(frame, "Error cancelling ticket!");
    }
}

    private void loadTicketQueue() {
        try {
            int technicianId = new TechniciansDAO(DBConnection.connect())
                    .getTechnicianIdByUserId(user.getUserID());
            List<Tickets> tickets = ticketsDAO.getTicketsByTechnicianID(technicianId);

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

    private void loadTicketHistory() {
        try {
            int technicianId = new TechniciansDAO(DBConnection.connect())
                    .getTechnicianIdByUserId(user.getUserID());
            List<Tickets> tickets = ticketsDAO.getResolvedTickets(technicianId);

            for (Tickets t : tickets) {
                ticketHistoryPanel.addTicket(
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
            // Get technician ID from the logged-in user
            int technicianId = new TechniciansDAO(DBConnection.connect())
                    .getTechnicianIdByUserId(user.getUserID());

            // Fetch tickets assigned to this technician
            List<Tickets> tickets = ticketsDAO.getTicketsByTechnician(technicianId);

            javax.swing.JComboBox<String> ticketsComboBox = resolveTicketTechnicianPanel.getTicketsToResolve();

            if (tickets.isEmpty()) {
                // No tickets assigned
                ticketsComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"No Tickets"}));
                resolveTicketTechnicianPanel.setTicketsList(tickets);
                return;
            }

            // Build combo box labels
            String[] ticketLabels = tickets.stream()
                    .map(t -> "Ticket#" + t.getTicket_id())
                    .toArray(String[]::new);

            // Set model and store ticket list in the panel
            ticketsComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(ticketLabels));
            resolveTicketTechnicianPanel.setTicketsList(tickets);

            // Select first ticket and update details
            ticketsComboBox.setSelectedIndex(0);
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

        resolveTicketTechnicianPanel.getTicketsDescriptionLabel().setText(selected.getTicket_description());

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

        // Get status selected by technician
        String newStatus = (String) resolveTicketTechnicianPanel.getStatus().getSelectedItem();
        if ("Mark Ticket Resolution Status".equals(newStatus)) {
            JOptionPane.showMessageDialog(frame, "No resolution selected — defaulting status to Active.");
            newStatus = "Active";
        }
        selected.setStatus(newStatus);

        // Update category if changed
        CategoryItem selectedCategory = (CategoryItem) resolveTicketTechnicianPanel.getCategories().getSelectedItem();
        if (selectedCategory != null) {
            selected.setCategory_id(selectedCategory.getId());
        }

        try {
            // Update Tickets table
            boolean success = ticketsDAO.updateTicket(selected);
            if (!success) {
                JOptionPane.showMessageDialog(frame, "Update failed. Please try again.");
                return;
            }

            // If ticket is resolved, update ResolvedTickets table
            if ("Resolved".equalsIgnoreCase(newStatus)) {
                String resolveDateText = resolveTicketTechnicianPanel.getResolveDateLabel().getText();
                ticketsDAO.markTicketResolved(selected.getTicket_id(),
                        (resolveDateText != null && !resolveDateText.isBlank()) ? resolveDateText : null);
                activateNextEnqueuedTicket();
            }

            // Optionally: handle Cancelled tickets here if needed

            JOptionPane.showMessageDialog(frame, "Ticket updated successfully!");
            loadAssignedTickets();
            panel.showPanel(TechnicianDashboardPanel.EMPTY_PANEL);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error updating ticket!");
        }
    }


    private void activateNextEnqueuedTicket(){
        try {
            int technicianID = techDAO.getTechnicianIdByUserId(user.getUserID());

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

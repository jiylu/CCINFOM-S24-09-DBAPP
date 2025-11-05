package controllers;

import dao.CategoriesDAO;
import dao.TechniciansDAO;
import dao.TicketsDAO;
import dao.UserDAO;
import models.Tickets;
import models.User;
import view.Frame;
import view.technician.ResolveTicketTechnicianPanel;
import view.technician.TechnicianDashboardPanel;
import java.util.List;

public class TechnicianDashboardController {
    private User user;
    private Frame frame;
    private TechnicianDashboardPanel panel;
    private ResolveTicketTechnicianPanel resolveTicketTechnicianPanel;
    private TicketsDAO ticketsDAO;

    public TechnicianDashboardController(User user, Frame frame, TicketsDAO ticketsDAO){
        this.user = user;
        this.frame = frame;
        this.panel = frame.getTechnicianDashboardPanel();
        this.resolveTicketTechnicianPanel = panel.getResolveTicketTechnicianPanel();
        this.ticketsDAO = ticketsDAO;
    }

    public void init(){
        frame.showPanel(Frame.TECHNICIAN_PANEL);
        initListeners();
        loadAssignedTickets();
    }

    private void initListeners(){
        panel.getResolveTicketButton().addActionListener(e -> {
            loadAssignedTickets();
            panel.showPanel(TechnicianDashboardPanel.RESOLVE_TICKET);
        });
    }

    private void loadAssignedTickets() {
        try {
            int technicianId = user.getUserID();

            List<Tickets> tickets = ticketsDAO.getTicketsByTechnician(technicianId);

            if (tickets != null) {
//                resolveTicketTechnicianPanel.updateTicketTable(tickets);      // To be updated
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

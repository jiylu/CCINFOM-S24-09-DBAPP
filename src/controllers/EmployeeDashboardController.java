package controllers;

import dao.EmployeesDAO;
import dao.UserDAO;
import models.User;
import view.Frame;
import view.employee.*;


public class EmployeeDashboardController{
    private User user;
    private Frame frame;
    private UserDAO userDAO;
    private EmployeesDAO empDAO;
    private EmployeeDashboardPanel panel;
    private CreateTicketPanel createTicketPanel;
    private CancelTicketPanel cancelTicketPanel;
    private TicketHistoryPanel ticketHistoryPanel;

    public EmployeeDashboardController(User user, Frame frame, UserDAO userDAO, EmployeesDAO empDAO){
        this.user = user;
        this.frame = frame;
        this.userDAO = userDAO;
        this.empDAO = empDAO;
        this.panel = frame.getEmployeeDashboardPanel();
        this.createTicketPanel = panel.getCreateTicketPanel();
        this.cancelTicketPanel = panel.getCancelTicketPanel();
        this.ticketHistoryPanel = panel.getTicketHistoryPanel();
    }

    public void init(){
        frame.showPanel(Frame.EMPLOYEE_PANEL);
        initListeners();
    }

    private void initListeners(){

        panel.getCreateTicketButton().addActionListener(e->{
            panel.showPanel(EmployeeDashboardPanel.CREATE_TICKET);
        });

        panel.getCancelTicketButton().addActionListener(e->{
            panel.showPanel(EmployeeDashboardPanel.CANCEL_TICKET);
        });

        panel.getTicketHistoryButton().addActionListener(e->{
            panel.showPanel(EmployeeDashboardPanel.TICKET_HISTORY);
        });
    }


    
}

package controllers;

import dao.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import models.*;
import view.Frame;
import view.employee.*;
import view.technician.*;


public class EmployeeDashboardController{

    private int lastTechIndex = -1;
    private EmpUser user;
    private Frame frame;
    private UserDAO userDAO;
    private EmployeesDAO empDAO;
    private TechniciansDAO techniciansDAO;
    private TicketsDAO ticketsDAO;
    private CategoriesDAO categoriesDAO;
    private EmployeeDashboardPanel panel;
    private CreateTicketPanel createTicketPanel;
    private TicketHistoryPanel ticketHistoryPanel;

    public EmployeeDashboardController(EmpUser user, Frame frame, UserDAO userDAO, EmployeesDAO empDAO, 
                                       TechniciansDAO techniciansDAO, TicketsDAO ticketsDAO, CategoriesDAO categoriesDAO){
        this.user = user;
        this.frame = frame;
        this.userDAO = userDAO;
        this.empDAO = empDAO;
        this.techniciansDAO = techniciansDAO;
        this.ticketsDAO = ticketsDAO;
        this.categoriesDAO = categoriesDAO;
        this.panel = frame.getEmployeeDashboardPanel();
        this.createTicketPanel = panel.getCreateTicketPanel();
        this.ticketHistoryPanel = panel.getTicketHistoryPanel();
    }

    public void init(){
        frame.showPanel(Frame.EMPLOYEE_PANEL);
        loadCategories();
        initListeners();
    }

    private void loadCategories() {
    try {
        List<Categories> allCategories = categoriesDAO.getAllCategories(); 
        JComboBox<CategoryItem> comboBox = createTicketPanel.getCategories();

        comboBox.removeAllItems();
        comboBox.addItem(new CategoryItem(-1, "- Select Category -"));

        for (Categories c : allCategories) {
            comboBox.addItem(new CategoryItem(c.getCategoryID(), c.getCategoryName()));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    }


    private void initListeners(){

        panel.getCreateTicketButton().addActionListener(e->{
            panel.showPanel(EmployeeDashboardPanel.CREATE_TICKET);
        });

        panel.getTicketHistoryButton().addActionListener(e->{
            panel.showPanel(EmployeeDashboardPanel.TICKET_HISTORY);
            viewTicketHistory();
        });

        createTicketPanel.getCreateButton().addActionListener(e -> {
            createTicket();
        });

    }

    private void createTicket() {
        String subject = createTicketPanel.getSubjectField().getText();
        String description = createTicketPanel.getDescription().getText();
        CategoryItem selectedCategory = (CategoryItem) createTicketPanel.getCategories().getSelectedItem();


        if (subject.isEmpty() || selectedCategory == null || selectedCategory.getId() == -1 || description.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (subject.length() > 100) {
            JOptionPane.showMessageDialog(frame, "Ticket's subject cannot exceed 100 characters. Kindly shorten your subject.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (description.length() > 250) {
            JOptionPane.showMessageDialog(frame, "Ticket's description cannot exceed 250 characters. Kindly shorten your description.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {

            Employees emp = empDAO.getEmployeeByUserId(user.getUserID());
            if (emp == null) {
                JOptionPane.showMessageDialog(frame, "Employee record not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int availableTechId = ticketsDAO.getAvailableTechnicianId();
            String status = (availableTechId != -1) ? "Active" : "Enqueued";

            Tickets newTicket = new Tickets();
            newTicket.setCategory_id(selectedCategory.getId());
            newTicket.setSubject(subject); 
            newTicket.setDescription(description);
            newTicket.setEmployee_id(emp.getEmpID());
            newTicket.setTechnician_id(getTechnicianId()); 
            newTicket.setCreation_date(java.time.LocalDateTime.now().toString());
            newTicket.setStatus(status);

            ticketsDAO.insertToTicketsTable(newTicket);

            JOptionPane.showMessageDialog(frame, "Ticket created successfully!");
            createTicketPanel.clearFields();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "An error occurred while creating the ticket.", "Error", JOptionPane.ERROR_MESSAGE);
        }
}

    private int getTechnicianId() { //tagakuha ng next tecgnician
        List<Technicians> allTechs = techniciansDAO.getAllTechnicians();
        if (allTechs == null || allTechs.isEmpty()) return 0; // fallback

        // increment index 
        lastTechIndex = (lastTechIndex + 1) % allTechs.size();

        return allTechs.get(lastTechIndex).getTechnician_id();
    }

    private void viewTicketHistory() {
        Employees employee = null;
        try {
            employee = empDAO.getEmployeeByUserId(user.getUserID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (employee == null) {
            JOptionPane.showMessageDialog(frame, "Employee record not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Tickets> empTickets = ticketsDAO.getTicketsByEmployeeId(employee.getEmpID());
        ticketHistoryPanel.loadTickets(empTickets);
    }
    
}

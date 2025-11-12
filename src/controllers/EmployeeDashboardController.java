package controllers;

import java.util.List;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import dao.*;
import models.*;
import view.Frame;
import view.employee.*;
import view.technician.*;


public class EmployeeDashboardController{
    private User user;
    private Frame frame;
    private UserDAO userDAO;
    private EmployeesDAO empDAO;
    private TechniciansDAO techniciansDAO;
    private TicketsDAO ticketsDAO;
    private CategoriesDAO categoriesDAO;
    private EmployeeDashboardPanel panel;
    private CreateTicketPanel createTicketPanel;
    private TicketHistoryPanel ticketHistoryPanel;

    public EmployeeDashboardController(User user, Frame frame, UserDAO userDAO, EmployeesDAO empDAO, 
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
        });

        createTicketPanel.getCreateButton().addActionListener(e -> {
            createTicket();
        });
    }

    private void createTicket() {
        String subject = createTicketPanel.getSubjectField().getText();
        CategoryItem selectedCategory = (CategoryItem) createTicketPanel.getCategories().getSelectedItem();


        if (subject.isEmpty() || selectedCategory == null || selectedCategory.getId() == -1) {
            JOptionPane.showMessageDialog(frame, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {

            Employees emp = empDAO.getEmployeeByUserId(user.getUserID());
            if (emp == null) {
                JOptionPane.showMessageDialog(frame, "Employee record not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Tickets newTicket = new Tickets();
            newTicket.setCategory_id(selectedCategory.getId());
            newTicket.setEmployee_id(emp.getEmpID());
            newTicket.setDepartment_id(emp.getDeptID());
            newTicket.setTechnician_id(getRandomTechnicianId()); 
            newTicket.setCreation_date(java.time.LocalDate.now().toString());
            newTicket.setResolve_date(null);
            newTicket.setStatus("Active");

            boolean success = ticketsDAO.insertTicket(newTicket);

            if (success) {
                JOptionPane.showMessageDialog(frame, "Ticket created successfully!");
                createTicketPanel.clearFields();
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to create ticket.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "An error occurred while creating the ticket.", "Error", JOptionPane.ERROR_MESSAGE);
        }
}

    private int getRandomTechnicianId() { //tagakuha ng random tecgnician
        List<Technicians> allTechs = techniciansDAO.getAllTechnicians();
        if (allTechs != null && !allTechs.isEmpty()) {
            Random rand = new Random();
            Technicians randomTech = allTechs.get(rand.nextInt(allTechs.size()));
            return randomTech.getTechnician_id(); 
        }
        return 0; 
    }




    
}

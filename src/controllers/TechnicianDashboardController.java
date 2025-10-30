package controllers;

import dao.CategoriesDAO;
import dao.TechniciansDAO;
import dao.UserDAO;
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
    private UserDAO userDAO;
    private TechniciansDAO techniciansDAO;
    private CategoriesDAO categoriesDAO;

    public TechnicianDashboardController(User user, Frame frame, UserDAO userDAO, TechniciansDAO techniciansDAO, CategoriesDAO categoriesDAO){
        this.user = user;
        this.frame = frame;
        this.panel = frame.getTechnicianDashboardPanel();
        this.resolveTicketTechnicianPanel = panel.getResolveTicketTechnicianPanel();
        this.userDAO = userDAO;
        this.techniciansDAO = techniciansDAO;
        this.categoriesDAO = categoriesDAO;
    }

    public void init(){
        frame.showPanel(Frame.TECHNICIAN_PANEL);
        initListeners();
    }

    private void initListeners(){
        panel.getResolveTicketButton().addActionListener(e -> {
            resolveTicketCategoriesFunctionality();
            panel.showPanel(TechnicianDashboardPanel.RESOLVE_TICKET);
        });
    }

    private void resolveTicketCategoriesFunctionality() {
        try {
            List<String> categoryList = categoriesDAO.getAllCategoryNames();

            if (categoryList != null) {
                String[] categoryArray = categoryList.toArray(new String[0]);
                resolveTicketTechnicianPanel.getCategories()
                        .setModel(new javax.swing.DefaultComboBoxModel<>(categoryArray));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

package controllers.admin;

import java.util.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.TicketsDAO;
import models.Categories;
import models.Department;
import util.ButtonRenderer;
import dao.CategoriesDAO;
import view.admin.ManageCategoriesPanel;
import view.admin.AdminDashboardPanel;

public class ManageCategoriesController {
    private ManageCategoriesPanel panel;
    private CategoriesDAO categoryDAO;
    private TicketsDAO ticketsDAO; 

    public ManageCategoriesController(ManageCategoriesPanel panel, CategoriesDAO categoryDAO, TicketsDAO ticketsDAO) {
        this.panel = panel;
        this.categoryDAO = categoryDAO;
        this.ticketsDAO = ticketsDAO;
    }

    public void init(AdminDashboardPanel adminPanel) {
        adminPanel.showPanel(AdminDashboardPanel.VIEW_CATEGORIES);
        List<Categories> categoryList = categoryDAO.getAllCategories();
        loadCategoryTable(categoryList);        
    }

    public void initListeners() {
        panel.getAddCategoryButton().addActionListener(e -> {
            addCategory();

            //editCategory();
        });
    }

    private void loadCategoryTable(List<Categories> list) {
        panel.setupCategoryTable(list);
        JTable table = panel.getCategoryTable();
        table.getColumn("Edit").setCellRenderer(new ButtonRenderer("Edit"));
    }

    private void addCategory() {
        JPanel panel = new JPanel();
        JLabel catNameLabel = new JLabel("Enter Category Name");
        JTextField textField = new JTextField(20); 
        panel.add(catNameLabel);
        panel.add(textField);

        int res = JOptionPane.showConfirmDialog(null, panel, "Add Catrgory", JOptionPane.OK_CANCEL_OPTION);

        if (res == JOptionPane.YES_OPTION){
            String catName = textField.getText().trim();
            
            if (isExistingCategory(catName)){
                return;
            }

            categoryDAO.insertCategory(catName);
            JOptionPane.showMessageDialog(null, "Inserted " + catName);
            loadCategoryTable(categoryDAO.getAllCategories());
        }  
    }

    public boolean isExistingCategory(String cat){
        if (categoryDAO.getCategoryIDByName(cat) != null){
            JOptionPane.showMessageDialog(null, "Category already exists!", "Input error", JOptionPane.WARNING_MESSAGE);
            return true;
        }        

        return false;
    }
    
}

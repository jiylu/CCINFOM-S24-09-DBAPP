package controllers.admin;

import java.sql.SQLException;
import java.util.*;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.TicketsDAO;
import models.Categories;
import models.Department;
import util.ButtonEditor;
import util.ButtonRenderer;
import dao.CategoriesDAO;
import view.admin.ManageCategoriesPanel;
import view.admin.AdminDashboardPanel;

public class ManageCategoriesController {
    private ManageCategoriesPanel panel;
    private CategoriesDAO categoryDAO;
    private TicketsDAO ticketsDAO;
    private boolean showSelectColumn;

    public ManageCategoriesController(ManageCategoriesPanel panel, CategoriesDAO categoryDAO, TicketsDAO ticketsDAO) {
        this.panel = panel;
        this.categoryDAO = categoryDAO;
        this.ticketsDAO = ticketsDAO;
        this.showSelectColumn = false;
    }

    public void init(AdminDashboardPanel adminPanel) {
        adminPanel.showPanel(AdminDashboardPanel.VIEW_CATEGORIES);
        List<Categories> categoryList = categoryDAO.getAllCategoryNameID();
        loadCategoryTable(categoryList, showSelectColumn);
    }

    public void initListeners() {
        panel.getAddCategoryButton().addActionListener(e -> {
            addCategory();
        });

        panel.getSelectColumnButton().addActionListener(e -> {
            showSelectColumn = true;
            panel.getDeleteCategoryButton().setVisible(true);
            loadCategoryTable(categoryDAO.getAllCategoryNameID(), showSelectColumn);
        });

        panel.getDeleteCategoryButton().addActionListener(e -> {
            deleteSelectedCategories();
        });
    }

    private void loadCategoryTable(List<Categories> list, boolean withSelectColumn) {
        if (withSelectColumn) {
            panel.setupCategoryTableWithSelect(list);
        } else {
            panel.setupCategoryTable(list);
        }

        JTable table = panel.getCategoryTable();

        try {
            table.getColumn("Edit").setCellRenderer(new ButtonRenderer("Edit"));
            initEditCategory(table);
        } catch (IllegalArgumentException e) {

        }
    }

    private void initEditCategory(JTable table){
        table.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox(), "Edit", row -> {
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Edit Category name");
            JTextField textField = new JTextField(20);
            int categoryID = (int) table.getValueAt(row, 0);
            String currCategoryName = table.getValueAt(row, 1).toString();

            textField.setText(currCategoryName);
            panel.add(label);
            panel.add(textField);

            int res = JOptionPane.showConfirmDialog(null, panel, "Edit Category", JOptionPane.OK_CANCEL_OPTION);

            if (res == JOptionPane.YES_OPTION){
                String input = textField.getText().trim();
                
                if (input.length() < 3 || input.length() > 40) {
                    JOptionPane.showMessageDialog(null, "Category name cannot must be 3 - 40 characters long.", "Input Error",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (isExistingCategory(input)){
                    return;
                }

                categoryDAO.editCategoryByID(categoryID, input);
                JOptionPane.showMessageDialog(null, "Successfully edited CategoryID: " + categoryID);
                loadCategoryTable(categoryDAO.getAllCategoryNameID(), false);
            }
        }));
    }

    private void addCategory() {
        JPanel panel = new JPanel();
        JLabel catNameLabel = new JLabel("Enter Category Name");
        JTextField textField = new JTextField(20);
        panel.add(catNameLabel);
        panel.add(textField);

        int res = JOptionPane.showConfirmDialog(null, panel, "Add Catrgory", JOptionPane.OK_CANCEL_OPTION);

        if (res == JOptionPane.YES_OPTION) {
            String catName = textField.getText().trim();

            if (catName.length() < 3 || catName.length() > 40) {
                JOptionPane.showMessageDialog(null, "Category name cannot must be 3 - 40 characters long.", "Input Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (isExistingCategory(catName)) {
                return;
            }

            categoryDAO.insertCategory(catName);
            JOptionPane.showMessageDialog(null, "Inserted " + catName);
            loadCategoryTable(categoryDAO.getAllCategoryNameID(), false);
            this.panel.getDeleteCategoryButton().setVisible(false);
        }
    }

    private void deleteSelectedCategories() {
        JTable table = panel.getCategoryTable();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        List<Integer> idsToDelete = new ArrayList<>();
        List<String> blockedCategories = new ArrayList<>();

        for (int i = 0; i < model.getRowCount(); i++) {
            boolean isSelected = Boolean.TRUE.equals(model.getValueAt(i, 0));
            if (isSelected) {
                int categoryId = (int) model.getValueAt(i, 1);
                try {
                    if (!ticketsDAO.getTicketsByCategoryID(categoryId).isEmpty()) {
                        blockedCategories.add(model.getValueAt(i, 2).toString());
                        continue;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "Error checking tickets for category ID " + categoryId,
                            "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                idsToDelete.add(categoryId);
            }
        }

        if (!blockedCategories.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Cannot delete " + String.join(", ", blockedCategories)
                            + ". There are tickets containing this category (Issue Type).",
                    "Warning", JOptionPane.WARNING_MESSAGE);
        }

        if (idsToDelete.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No categories selected for deletion.");
            loadCategoryTable(categoryDAO.getAllCategoryNameID(), false);
            panel.getDeleteCategoryButton().setVisible(false);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete " + idsToDelete.size() + " categories?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            categoryDAO.deactivateCategories(idsToDelete);
            JOptionPane.showMessageDialog(null, "Categories deleted.");
            loadCategoryTable(categoryDAO.getAllCategoryNameID(), false);
            panel.getDeleteCategoryButton().setVisible(false);
        }
    }

    public boolean isExistingCategory(String cat) {
        if (categoryDAO.getCategoryIDByName(cat) != null) {
            JOptionPane.showMessageDialog(null, "Category already exists!", "Input error", JOptionPane.WARNING_MESSAGE);
            return true;
        }

        return false;
    }
}

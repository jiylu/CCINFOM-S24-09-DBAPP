package view.admin;

import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import models.Categories;

public class ManageCategoriesPanel extends JPanel {
    private JTable table;
    private JScrollPane tableScrollPane;
    private JButton addCategoryButton;
    private JButton deleteCategoryButton;

    public ManageCategoriesPanel() {
        setLayout(null);
        setupAddCategoryButton();
        setupDeleteCategoryButton();
    }

    private void setupAddCategoryButton() {
        addCategoryButton = new JButton("Add Category");
        addCategoryButton.setBounds(0, 20, 130, 25);
        add(addCategoryButton);
    }

    private void setupDeleteCategoryButton() {
        deleteCategoryButton = new JButton("Delete Category");
        deleteCategoryButton.setBounds(150, 20, 130, 25);
        add(deleteCategoryButton);
    }

    private DefaultTableModel setupTable(String[] cols) {
        if (tableScrollPane != null) {
            remove(tableScrollPane);
        }

        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                String colName = getColumnName(column);
                return colName.equals("Edit");
            }
        };

        table = new JTable(model);
        tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(0, 60, 1160, 630);
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(tableScrollPane);

        revalidate();
        repaint();

        return model;
    }

    public void setupCategoryTable(List<Categories> catList) {
        String[] cols = { "Category ID", "Category Name", "Edit" };
        DefaultTableModel model = setupTable(cols);
        for (Categories cat : catList) {
            Object[] row = { cat.getCategoryID(), cat.getCategoryName(), "Edit" };
            model.addRow(row);
        }

        table.setModel(model);
    }

    public JButton getAddCategoryButton() {
        return addCategoryButton;
    }

    public JButton getDeleteCategoryButton() {
        return deleteCategoryButton;
    }

    public JTable getCategoryTable() {
        return table;
    }
}

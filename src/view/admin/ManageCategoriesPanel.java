package view.admin;

import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import models.Categories;

public class ManageCategoriesPanel extends JPanel {
    private JTable table;
    private JScrollPane tableScrollPane;
    private JButton addCategoryButton;
    private JButton deleteCategoryButton;
    private JButton selectColumnButton;

    public ManageCategoriesPanel() {
        setLayout(null);
        setBackground(new Color(245, 245, 245)); // light gray background

        setupAddCategoryButton();
        setupSelectColumnButton();
        setupDeleteCategoryButton();
    }

    private void setupAddCategoryButton() {
        addCategoryButton = new JButton("Add Category");
        styleButton(addCategoryButton);
        addCategoryButton.setBounds(20, 20, 150, 35);
        add(addCategoryButton);
    }

    private void setupSelectColumnButton() {
        selectColumnButton = new JButton("Select Column To Delete");
        styleButton(selectColumnButton);
        selectColumnButton.setBounds(180, 20, 220, 35);
        add(selectColumnButton);
    }

    private void setupDeleteCategoryButton() {
        deleteCategoryButton = new JButton("Delete Category");
        styleButton(deleteCategoryButton);
        deleteCategoryButton.setBounds(410, 20, 150, 35);
        deleteCategoryButton.setVisible(false);
        add(deleteCategoryButton);
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(0, 102, 204)); // blue button
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        // hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 80, 160));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 102, 204));
            }
        });
    }

    private DefaultTableModel setupTable(String[] cols) {
        if (tableScrollPane != null) {
            remove(tableScrollPane);
        }

        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                String colName = getColumnName(columnIndex);
                if ("Select".equals(colName)) return Boolean.class;
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                String colName = getColumnName(column);
                return colName.equals("Edit") || colName.equals("Select");
            }
        };

        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(0, 102, 204));
        table.getTableHeader().setForeground(Color.WHITE);

        tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(20, 70, 1120, 500);
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(tableScrollPane);

        revalidate();
        repaint();

        return model;
    }

    public void setupCategoryTable(List<Categories> catList) {
        String[] cols = {"Category ID", "Category Name", "Edit"};
        DefaultTableModel model = setupTable(cols);

        for (Categories cat : catList) {
            Object[] row = {cat.getCategoryID(), cat.getCategoryName(), "Edit"};
            model.addRow(row);
        }

        table.setModel(model);
    }

    public void setupCategoryTableWithSelect(List<Categories> catList) {
        String[] cols = {"Select", "Category ID", "Category Name"};
        DefaultTableModel model = setupTable(cols);

        for (Categories cat : catList) {
            Object[] row = {false, cat.getCategoryID(), cat.getCategoryName()};
            model.addRow(row);
        }

        table.setModel(model);
        table.getColumn("Select").setPreferredWidth(50);
        table.getColumn("Select").setMaxWidth(50);
        table.getColumn("Select").setMinWidth(50);
    }

    public JButton getAddCategoryButton() {
        return addCategoryButton;
    }

    public JButton getSelectColumnButton() {
        return selectColumnButton;
    }

    public JButton getDeleteCategoryButton() {
        return deleteCategoryButton;
    }

    public JTable getCategoryTable() {
        return table;
    }
}

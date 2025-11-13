package view.employee;

import view.technician.CategoryItem;
import javax.swing.*;
import java.awt.*;

public class CreateTicketPanel extends JPanel {
    private JLabel titleLabel;
    private JTextField subjectField;
    private JComboBox<CategoryItem> categories;
    private JTextArea descriptionArea;
    private JButton createButton;

    public CreateTicketPanel(){
        setLayout(null);
        initTitle();
        setSubject();
        setupCategories();
        setupCommentSec();
        createTicketButton();
    }

    private void initTitle() {
        titleLabel = new JLabel("Create a Ticket");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 35));
        titleLabel.setBounds(300, 70, 500, 35);
        add(titleLabel);
    }

    private void setSubject() {
        JLabel lblTitle = new JLabel("Ticket Subject:");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(190, 170, 150, 25);
        add(lblTitle);

        subjectField = new JTextField();
        subjectField.setBounds(360, 170, 300, 25);
        add(subjectField);
    }

    private void setupCategories() {
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 20));
        categoryLabel.setBounds(190, 220, 120, 25);
        add(categoryLabel);

        categories = new JComboBox<>();             
        categories.setBounds(360, 220, 300, 25);
        add(categories);
    }

    private void setupCommentSec(){
        JLabel lblDescription = new JLabel("Add comments/ description for technician:");
        lblDescription.setFont(new Font("Arial", Font.PLAIN, 15));
        lblDescription.setBounds(190, 260, 500, 70);
        add(lblDescription);

        descriptionArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        scrollPane.setBounds(190, 310, 510, 120);
        add(scrollPane);
    }

    private void createTicketButton(){
        createButton = new JButton("Create Ticket");
        createButton.setBounds(360, 480, 140, 40);
        add(createButton);
    }

    public JComboBox<CategoryItem> getCategories(){
        return categories;
    }

    public JButton getCreateButton() {
        return createButton;
    }

    public JTextField getSubjectField() {
    return subjectField;
    }

    public JTextArea getDescription() {
        return descriptionArea;
    }

    public void clearFields() {
        subjectField.setText("");
        descriptionArea.setText("");
        categories.setSelectedIndex(0);
    }
}
package view.employee;

import models.Categories;
import javax.swing.*;
import java.awt.*;

public class CreateTicketPanel extends JPanel {
    private JLabel titleLabel;
    private JTextField subjectField;
    private JComboBox<Categories> categories;
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
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(120, 20, 300, 35);
        add(titleLabel);
    }

    private void setSubject() {
        JLabel lblTitle = new JLabel("Ticket Subject:");
        lblTitle.setBounds(120, 80, 100, 25);
        add(lblTitle);

        subjectField = new JTextField();
        subjectField.setBounds(220, 80, 250, 30);
        add(subjectField);
    }

    private void setupCategories() {
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(120,120,120,25);
        add(categoryLabel);

        JComboBox<String> categories = new JComboBox<>();
        categories.addItem("- Select Category -");
        categories.addItem("Network Issue");
        categories.addItem("Software Issue");
        categories.addItem("Hardware Issue");
        categories.addItem("Account Access Issue");
    
        categories.setBounds(220, 120, 250, 30);
        add(categories);
    }

    private void setupCommentSec(){
        JLabel lblDescription = new JLabel("Add comments/ description for technician:");
        lblDescription.setBounds(120, 150, 500, 70);
        add(lblDescription);

        descriptionArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        scrollPane.setBounds(120, 200, 370, 120);
        add(scrollPane);
    }

    private void createTicketButton(){
        createButton = new JButton("Create Ticket");
        createButton.setBounds(250, 350, 100, 35);
        add(createButton);
    }

    public JComboBox<Categories> getCategories(){
        return categories;
    }
    

}

package view.employee;

import javax.swing.*;
import java.awt.*;

public class CreateTicketPanel extends JPanel {
    private JLabel titleLabel;

    public CreateTicketPanel(){
        setLayout(null);
        initTitle();
    }

    private void initTitle() {
        titleLabel = new JLabel("Create New Ticket!!!!!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(10, 10, 300, 30);
        add(titleLabel);
    }

}

package view.employee;

import javax.swing.*;
import java.awt.*;

public class CancelTicketPanel extends JPanel {
    private JLabel titleLabel;

    public CancelTicketPanel(){
        setLayout(null);
        initTitle();
    }

    private void initTitle() {
        titleLabel = new JLabel("Cancel Ticket!!!!!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(10, 10, 300, 30);
        add(titleLabel);
    }
    
}

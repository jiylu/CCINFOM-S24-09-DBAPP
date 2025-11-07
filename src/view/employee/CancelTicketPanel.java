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
        titleLabel = new JLabel("Cancel a Ticket");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(120, 20, 300, 35);
        add(titleLabel);
    }
    
}

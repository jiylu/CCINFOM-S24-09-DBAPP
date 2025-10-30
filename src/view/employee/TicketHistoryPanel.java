package view.employee;

import javax.swing.*;
import java.awt.*;

public class TicketHistoryPanel extends JPanel {
    private JLabel titleLabel;

    public TicketHistoryPanel() {
        setLayout(null);
        initTitle();
    }

    private void initTitle() {
        titleLabel = new JLabel("Ticket History!!!!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(10, 10, 300, 30);
        add(titleLabel);
    }
}

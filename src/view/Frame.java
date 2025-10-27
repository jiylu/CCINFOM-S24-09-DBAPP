package view;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame{
    public final static String LOGIN_PANEL = "loginPanel";

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private LoginPanel loginPanel;

    public Frame(){
        super("IT Helpdesk Ticketing System");
        setupFrameComponents();
        loginPanel = new LoginPanel();
        setupCardLayout();
        setVisible(true);
    }

    private void setupFrameComponents(){
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    private void setupCardLayout(){
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(loginPanel, LOGIN_PANEL);
        add(cardPanel);
    }

    public void showPanel(String name){
        cardLayout.show(cardPanel, name);
    }

    public LoginPanel getLoginPanel(){
        return loginPanel;
    }
}

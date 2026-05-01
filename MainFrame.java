package com.first;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {

        AdvanceUi.applyModernTheme();

        setTitle("Vehicle Rental Management System");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Vehicle Rental System", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        buttonPanel.setBackground(Color.WHITE);

        JButton viewBtn = AdvanceUi.styledButton("View Vehicles");
        JButton rentBtn = AdvanceUi.styledButton("Rent Vehicle");
        JButton returnBtn = AdvanceUi.styledButton("Return Vehicle");
        JButton addBtn = AdvanceUi.styledButton("Add Vehicle");
        JButton updateBtn = AdvanceUi.styledButton("Update Price");
        JButton deleteBtn = AdvanceUi.styledButton("Delete Vehicle");

        buttonPanel.add(viewBtn);
        buttonPanel.add(rentBtn);
        buttonPanel.add(returnBtn);
        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }
}
package com.first;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddVehiclesFrame extends JFrame {

    private JTextField nameField;
    private JTextField typeField;
    private JTextField priceField;

    public AddVehiclesFrame() {

        // Apply Modern Theme
        AdvanceUi.applyModernTheme();

        setTitle("Add New Vehicle");
        setSize(500, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // ===== Title =====
        JLabel titleLabel = new JLabel("Add Vehicle", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // ===== Form Panel =====
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Vehicle Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(AdvanceUi.styledLabel("Vehicle Name:"), gbc);

        gbc.gridx = 1;
        nameField = AdvanceUi.styledTextField();
        formPanel.add(nameField, gbc);

        // Vehicle Type
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(AdvanceUi.styledLabel("Vehicle Type:"), gbc);

        gbc.gridx = 1;
        typeField = AdvanceUi.styledTextField();
        formPanel.add(typeField, gbc);

        // Price Per Day
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(AdvanceUi.styledLabel("Price Per Day:"), gbc);

        gbc.gridx = 1;
        priceField = AdvanceUi.styledTextField();
        formPanel.add(priceField, gbc);

        // ===== Button Panel =====
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton addButton = AdvanceUi.styledButton("Add Vehicle");
        formPanel.add(addButton, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        add(mainPanel);

        // ===== Button Action (Keep Your Existing Logic Here) =====
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                String type = typeField.getText();
                String price = priceField.getText();

                if (name.isEmpty() || type.isEmpty() || price.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Please fill all fields!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    double priceValue = Double.parseDouble(price);

                    // ================================
                    // ⚠️ DO NOT CHANGE YOUR DAO CODE
                    // Call your existing method here
                    // Example:
                    // VehicleDAO.addVehicle(name, type, priceValue);
                    // ================================

                    JOptionPane.showMessageDialog(null,
                            "Vehicle Added Successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    nameField.setText("");
                    typeField.setText("");
                    priceField.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Price must be a valid number!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }
}
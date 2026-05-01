package com.first;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ReturnVehicleFrame extends JFrame {

    public ReturnVehicleFrame() {

        setTitle("Return Vehicle");
        setSize(700, 400);
        setLayout(new GridLayout(2, 2, 5, 5));
        setLocationRelativeTo(null);

        JTextField id = new JTextField();
        JButton returnBtn = new JButton("Mark Available");

        add(new JLabel("Vehicle ID:")); add(id);
        add(new JLabel("")); add(returnBtn);

        returnBtn.addActionListener(e -> {
            VehicleDAO.markAsAvailable(Integer.parseInt(id.getText()));
            JOptionPane.showMessageDialog(this, "Vehicle Returned!");
        });

        setVisible(true);
    }
}

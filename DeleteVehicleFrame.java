package com.first;

import javax.swing.*;
import java.awt.*;

public class DeleteVehicleFrame extends JFrame {

    public DeleteVehicleFrame() {

        setTitle("Delete Vehicle");
        setSize(500, 200);
        setLayout(new GridLayout(2, 2, 5, 5));
        setLocationRelativeTo(null);

        JTextField id = new JTextField();
        JButton deleteBtn = new JButton("Delete");

        add(new JLabel("Vehicle ID:")); add(id);
        add(new JLabel("")); add(deleteBtn);

        deleteBtn.addActionListener(e -> {
            VehicleDAO.deleteVehicle(Integer.parseInt(id.getText()));
            JOptionPane.showMessageDialog(this, "Vehicle Deleted!");
        });

        setVisible(true);
    }
}

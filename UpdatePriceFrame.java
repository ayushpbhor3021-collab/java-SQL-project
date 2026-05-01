package com.first;

import javax.swing.*;
import java.awt.*;

public class UpdatePriceFrame extends JFrame {

    public UpdatePriceFrame() {

        setTitle("Update Price");
        setSize(700, 400);
        setLayout(new GridLayout(3, 2, 5, 5));
        setLocationRelativeTo(null);

        JTextField id = new JTextField();
        JTextField price = new JTextField();

        JButton updateBtn = new JButton("Update");

        add(new JLabel("Vehicle ID:")); add(id);
        add(new JLabel("New Price:")); add(price);
        add(new JLabel("")); add(updateBtn);

        updateBtn.addActionListener(e -> {
            VehicleDAO.updatePrice(
                    Integer.parseInt(id.getText()),
                    Double.parseDouble(price.getText())
            );
            JOptionPane.showMessageDialog(this, "Price Updated!");
        });

        setVisible(true);
    }
}

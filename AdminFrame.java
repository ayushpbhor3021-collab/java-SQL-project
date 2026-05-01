package com.first;

import javax.swing.*;
import java.awt.*;

public class AdminFrame extends JFrame {

    public AdminFrame() {

        setTitle("Admin Panel");
        setSize(700, 400);
        setLayout(new GridLayout(6, 1, 10, 10));
        setLocationRelativeTo(null);

        JButton addBtn = new JButton("Add Vehicle");
        JButton updateBtn = new JButton("Update Price");
        JButton historyBtn = new JButton("View Rent History");
        JButton deleteBtn = new JButton("Delete Vehicle");
        JButton returnBtn = new JButton("Mark Vehicle Available");

        add(addBtn);
        add(updateBtn);
        add(historyBtn);
        add(deleteBtn);
        add(returnBtn);

        addBtn.addActionListener(e -> new AddVehiclesFrame());
        updateBtn.addActionListener(e -> new UpdatePriceFrame());
        historyBtn.addActionListener(e -> RentalDAO.viewAllRentals());
        deleteBtn.addActionListener(e -> new DeleteVehicleFrame());
        returnBtn.addActionListener(e -> new ReturnVehicleFrame());

        setVisible(true);
    }
}

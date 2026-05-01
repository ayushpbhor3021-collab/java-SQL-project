package com.first;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RentVehicleFrame extends JFrame {

    public RentVehicleFrame() {

        setTitle("Rent Vehicle");
        setSize(500, 350);
        setLayout(new GridLayout(8, 2, 5, 5));
        setLocationRelativeTo(null);

        JTextField name = new JTextField();
        JTextField license = new JTextField();
        JTextField aadhar = new JTextField();
        JTextField address = new JTextField();
        JTextField vehicleId = new JTextField();
        JTextField days = new JTextField();

        JButton rentBtn = new JButton("Rent Now");

        add(new JLabel("Customer Name:")); add(name);
        add(new JLabel("License:")); add(license);
        add(new JLabel("Aadhar:")); add(aadhar);
        add(new JLabel("Address:")); add(address);
        add(new JLabel("Vehicle ID:")); add(vehicleId);
        add(new JLabel("Days:")); add(days);
        add(new JLabel("")); add(rentBtn);

        rentBtn.addActionListener(e -> {

            try (Connection con = DBConnection.getConnection()) {

                int id = Integer.parseInt(vehicleId.getText());
                int rentDays = Integer.parseInt(days.getText());

                PreparedStatement ps = con.prepareStatement(
                        "SELECT price_per_day FROM vehicles WHERE id=? AND is_available=true");
                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    double price = rs.getDouble("price_per_day");
                    double total = price * rentDays;

                    VehicleDAO.markAsRented(id);
                    RentalDAO.saveRental(
                            name.getText(),
                            license.getText(),
                            aadhar.getText(),
                            address.getText(),
                            id,
                            rentDays,
                            total
                    );

                    JOptionPane.showMessageDialog(this,
                            "Vehicle Rented Successfully!\nTotal: " + total);

                } else {
                    JOptionPane.showMessageDialog(this,
                            "Vehicle Not Available!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setVisible(true);
    }
}

package com.first;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RentalDAO {

    // ================= SAVE RENTAL =================
    public static void saveRental(String customerName,
                                  String license,
                                  String aadhar,
                                  String address,
                                  int vehicleId,
                                  int days,
                                  double total) {

        String sql = "INSERT INTO rentals " +
                "(customer_name, license, aadhar, address, vehicle_id, days, total_amount) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, customerName);
            ps.setString(2, license);
            ps.setString(3, aadhar);
            ps.setString(4, address);
            ps.setInt(5, vehicleId);
            ps.setInt(6, days);
            ps.setDouble(7, total);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Rental Record Saved Successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= VIEW ALL RENTALS =================
    public static void viewAllRentals() {

        String sql = "SELECT * FROM rentals";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n========== RENT HISTORY ==========");

            while (rs.next()) {

                System.out.println("Rental ID: " + rs.getInt("id"));
                System.out.println("Customer Name: " + rs.getString("customer_name"));
                System.out.println("License: " + rs.getString("license"));
                System.out.println("Aadhar: " + rs.getString("aadhar"));
                System.out.println("Address: " + rs.getString("address"));
                System.out.println("Vehicle ID: " + rs.getInt("vehicle_id"));
                System.out.println("Days: " + rs.getInt("days"));
                System.out.println("Total Amount: " + rs.getDouble("total_amount"));
                System.out.println("Rent Date: " + rs.getTimestamp("rent_date"));
                System.out.println("-----------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

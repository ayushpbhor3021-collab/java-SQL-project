package com.first;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Vehicle_System {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n====== VEHICLE RENTAL SYSTEM ======");
            System.out.println("1. Visit Rental System");
            System.out.println("2. Take Vehicle On Rent");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    visitFlow(sc);
                    break;

                case 2:
                    takeOnRentFlow(sc);
                    break;

                case 3:
                    Admin.adminLogin(sc);
                    break;

                case 4:
                    System.out.println("Thank You For Visiting!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    // ================= VISIT FLOW =================
    public static void visitFlow(Scanner sc) {

        String type = selectType(sc);
        VehicleDAO.showVehiclesByType(type);

        System.out.println("\nEnter Vehicle ID to View Details:");
        int vehicleId = sc.nextInt();
        sc.nextLine();

        String sql = "SELECT * FROM vehicles WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, vehicleId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\n===== VEHICLE DETAILS =====");
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Type: " + rs.getString("type"));
                System.out.println("Fuel: " + rs.getString("fuel"));
                System.out.println("Mileage: " + rs.getInt("mileage"));
                System.out.println("Price Per Day: " + rs.getDouble("price_per_day"));
                System.out.println("Available: " + rs.getBoolean("is_available"));

            } else {
                System.out.println("Vehicle Not Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= TAKE ON RENT FLOW =================
    public static void takeOnRentFlow(Scanner sc) {

        System.out.println("Enter Customer Name:");
        String customerName = sc.nextLine();

        System.out.println("Enter Driving License Number:");
        String license = sc.nextLine();

        if (license.length() < 5) {
            System.out.println("Invalid License Number!");
            return;
        }

        System.out.println("Enter Aadhar Number:");
        String aadhar = sc.nextLine();

        if (aadhar.length() != 12) {
            System.out.println("Aadhar must be 12 digits!");
            return;
        }

        System.out.println("Enter Address:");
        String address = sc.nextLine();

        if (address.isEmpty()) {
            System.out.println("Address cannot be empty!");
            return;
        }

        String type = selectType(sc);
        VehicleDAO.showVehiclesByType(type);

        System.out.println("\nEnter Vehicle ID to Rent:");
        int vehicleId = sc.nextInt();

        System.out.println("Enter number of days:");
        int days = sc.nextInt();
        sc.nextLine();

        String sql = "SELECT price_per_day, is_available FROM vehicles WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, vehicleId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                boolean available = rs.getBoolean("is_available");

                if (!available) {
                    System.out.println("Vehicle is not available!");
                    return;
                }

                double price = rs.getDouble("price_per_day");
                double total = price * days;

                // BILL
                System.out.println("\n========== BILL ==========");
                System.out.println("Total Days: " + days);
                System.out.println("Price Per Day: " + price);
                System.out.println("Total Amount: " + total);
                System.out.println("==========================");

                // Update Vehicle
                VehicleDAO.markAsRented(vehicleId);

                // Save Rental
                RentalDAO.saveRental(customerName, license,
                        aadhar, address,
                        vehicleId, days, total);

                System.out.println("Vehicle Rented Successfully!");

            } else {
                System.out.println("Vehicle Not Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= SELECT TYPE =================
    public static String selectType(Scanner sc) {

        System.out.println("\nSelect Vehicle Type:");
        System.out.println("1. Car");
        System.out.println("2. Bike");
        System.out.println("3. Sports Bike");
        System.out.println("4. Scooty");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                return "Car";
            case 2:
                return "Bike";
            case 3:
                return "Sports Bike";
            case 4:
                return "Scooty";
            default:
                System.out.println("Invalid Type Selected!");
                return selectType(sc);
        }
    }
}

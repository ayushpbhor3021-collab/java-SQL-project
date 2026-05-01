package com.first;

import java.util.Scanner;

public class Admin {

    public static void adminLogin(Scanner sc) {

        System.out.print("Enter Username: ");
        String username = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        if (authenticate(username, password)) {
            System.out.println("Login Successful!");
            adminPanel(sc);
        } else {
            System.out.println("Invalid Credentials!");
        }
    }

    private static boolean authenticate(String username, String password) {
        return username.equals("admin") && password.equals("1234");
    }

    // ================= ADMIN PANEL =================
    public static void adminPanel(Scanner sc) {

        int choice;

        do {
            System.out.println("\n======= ADMIN PANEL =======");
            System.out.println("1. Add New Vehicle");
            System.out.println("2. Update Vehicle Price");
            System.out.println("3. View All Rent History");
            System.out.println("4. Delete Vehicle");
            System.out.println("5. Mark Vehicle As Available (Return)");
            System.out.println("6. Logout");
            System.out.print("Enter Choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addVehicle(sc);
                    break;

                case 2:
                    updatePrice(sc);
                    break;

                case 3:
                    RentalDAO.viewAllRentals();
                    break;

                case 4:
                    deleteVehicle(sc);
                    break;

                case 5:
                    returnVehicle(sc);
                    break;

                case 6:
                    System.out.println("Logging Out...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 6);
    }

    // ================= ADD VEHICLE =================
    private static void addVehicle(Scanner sc) {

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Type: ");
        String type = sc.nextLine();

        System.out.print("Enter Fuel: ");
        String fuel = sc.nextLine();

        System.out.print("Enter Mileage: ");
        int mileage = sc.nextInt();

        System.out.print("Enter Price Per Day: ");
        double price = sc.nextDouble();
        sc.nextLine();

        VehicleDAO.addVehicle(name, type, fuel, mileage, price);
    }

    // ================= UPDATE PRICE =================
    private static void updatePrice(Scanner sc) {

        System.out.print("Enter Vehicle ID: ");
        int id = sc.nextInt();

        System.out.print("Enter New Price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        VehicleDAO.updatePrice(id, price);
    }

    // ================= DELETE VEHICLE =================
    private static void deleteVehicle(Scanner sc) {

        System.out.print("Enter Vehicle ID to Delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        VehicleDAO.deleteVehicle(id);
    }

    // ================= RETURN VEHICLE =================
    private static void returnVehicle(Scanner sc) {

        System.out.print("Enter Vehicle ID to Mark Available: ");
        int id = sc.nextInt();
        sc.nextLine();

        VehicleDAO.markAsAvailable(id);
    }
}

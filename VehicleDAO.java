package com.first;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VehicleDAO {

    // Show vehicles by type
    public static void showVehiclesByType(String type) {

        String sql = "SELECT * FROM vehicles WHERE type=? AND is_available=TRUE";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();

            System.out.println("\nAvailable " + type + "s:");

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                        " | Name: " + rs.getString("name") +
                        " | Price: " + rs.getDouble("price_per_day")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // ================= ADD VEHICLE =================
    public static void addVehicle(String name, String type,
            String fuel, int mileage, double price) {

		String sql = "INSERT INTO vehicles (name, type, fuel_type, mileage, price_per_day, is_available) VALUES (?, ?, ?, ?, ?, true)";
		
		try (Connection con = DBConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(sql)) {
		
		ps.setString(1, name);
		ps.setString(2, type);
		ps.setString(3, fuel);
		ps.setInt(4, mileage);
		ps.setDouble(5, price);
		
		ps.executeUpdate();
		System.out.println("Vehicle Added Successfully!");
		
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
		
		//================= UPDATE PRICE =================
		public static void updatePrice(int id, double price) {
		
		String sql = "UPDATE vehicles SET price_per_day=? WHERE id=?";
		
		try (Connection con = DBConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(sql)) {
		
		ps.setDouble(1, price);
		ps.setInt(2, id);
		
		int rows = ps.executeUpdate();
		
		if (rows > 0)
		System.out.println("Price Updated Successfully!");
		else
		System.out.println("Vehicle Not Found!");
		
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
		
		//================= DELETE VEHICLE =================
		public static void deleteVehicle(int id) {
		
		String sql = "DELETE FROM vehicles WHERE id=?";
		
		try (Connection con = DBConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(sql)) {
		
		ps.setInt(1, id);
		
		int rows = ps.executeUpdate();
		
		if (rows > 0)
		System.out.println("Vehicle Deleted Successfully!");
		else
		System.out.println("Vehicle Not Found!");
		
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
		
		//================= MARK AS AVAILABLE =================
		public static void markAsAvailable(int id) {
		
		String sql = "UPDATE vehicles SET is_available=true WHERE id=?";
		
		try (Connection con = DBConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(sql)) {
		
		ps.setInt(1, id);
		
		int rows = ps.executeUpdate();
		
		if (rows > 0)
		System.out.println("Vehicle Marked As Available!");
		else
		System.out.println("Vehicle Not Found!");
		
		} catch (Exception e) {
		e.printStackTrace();
		}
		}

    
    
    
    

    // Update availability after rent
    public static void markAsRented(int vehicleId) {

        String sql = "UPDATE vehicles SET is_available=FALSE WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, vehicleId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

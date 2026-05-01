package com.first;
public class Vehicle {

    String name;
    String type;
    String fuelType;
    int mileage;
    double pricePerDay;
    boolean isAvailable;

    public Vehicle(String name, String type, String fuelType, int mileage, double pricePerDay) {
        this.name = name;
        this.type = type;
        this.fuelType = fuelType;
        this.mileage = mileage;
        this.pricePerDay = pricePerDay;
        this.isAvailable = true;
    }

    public void displayDetails() {
        System.out.println("\nVehicle Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Mileage: " + mileage + " kmpl");
        System.out.println("Price Per Day: " + pricePerDay);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
    }
}

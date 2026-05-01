package com.first;

public class BillGenerator {

    public static double generateBill(Vehicle vehicle, int days) {

        double total = vehicle.pricePerDay * days;

        Thread loadingThread = new Thread(() -> {
            try {

                System.out.println("\n Generating Your Bill...");
                System.out.print("Progress: ");

                for (int i = 0; i <= 100; i += 10) {
                    Thread.sleep(300); // delay
                    System.out.print("█");
                }

                System.out.println(" 100%");
                System.out.println(" Bill Generated Successfully!\n");

            } catch (InterruptedException e) {
                System.out.println("Error in bill generation.");
            }
        });

        loadingThread.start();

        try {
            loadingThread.join(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

         System.out.println("══════════════════════════════════════");
        System.out.println("              RENTAL BILL             ");
        System.out.println("══════════════════════════════════════");
        System.out.println(" Vehicle        : " + vehicle.name);
        System.out.println(" Days           : " + days);
        System.out.println(" Price Per Day  : " + vehicle.pricePerDay);
        System.out.println("--------------------------------------");
        System.out.println(" Total Amount   : " + total);
        System.out.println("══════════════════════════════════════");
        System.out.println(" Thank You For Choosing Us!");
        System.out.println("══════════════════════════════════════");

        return total;
    }
}

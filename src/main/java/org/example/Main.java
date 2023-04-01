package org.example;

import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static Shop shop = new Shop("My Shop", "www.myshop.com", "909 230 12 02", 0.0);
    public static void main(String[] args) {

    }

    public void adminMenu(){
        while (true) {

            System.out.println("1. Add product");
            System.out.println("2. Add order");
            System.out.println("3. Add account");
            System.out.println("4. Add profit");
            System.out.println("5. List products");
            System.out.println("6. List orders");
            System.out.println("7. List accounts");
            System.out.println("8. Show total profit");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    // Add product
                    addProduct();
                    break;

                case 2:
                    // Add order
                    addOrder();
                    break;

                case 3:
                    // Add account
                    addAccount();
                    break;

                case 4:
                    // Add profit
                    System.out.print("Enter profit amount: ");
                    double profitAmount = input.nextDouble();
                    shop.addProfit(profitAmount);
                    System.out.println("Profit added successfully!");
                    break;

                case 5:
                    // List products
                    System.out.println("Product list:");
                    for (Product p : shop.getProductList()) {
                        System.out.println(p);
                    }
                    break;

                case 6:
                    // List orders
                    System.out.println("Order list:");
                    for (Order o : shop.getOrderList()) {
                        System.out.println(o);
                    }
                    break;

                case 7:
                    // List accounts
                    System.out.println("Account list:");
                    for (Account acc : shop.getAccountList()) {
                        System.out.println(acc);
                    }
                    break;

                case 8:
                    // Show total profit
                    System.out.println("Total profit: " + shop.getTotalProfit());
                    break;

                case 0:
                    // Exit
                    System.out.println("Goodbye!");
                    System.exit(0);

                default:
                    // Invalid choice
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}

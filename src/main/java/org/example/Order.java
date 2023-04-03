package org.example;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {

    private LocalDate date;
    private User user;
    private ArrayList<Product> purchasedProducts;
    private double totalPrice;
    private boolean verification;

    public Order(LocalDate date, User user, ArrayList<Product> purchasedProducts) {
        this.date = date;
        this.user = user;
        this.purchasedProducts = purchasedProducts;
        this.totalPrice = calculateTotalPrice();
        this.verification = false;
    }

    public LocalDate getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Product> getPurchasedProducts() {
        return purchasedProducts;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private double calculateTotalPrice() {
        double total = 0;
        for (Product product : purchasedProducts) {
            total += product.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("Order details:\n");
        info.append("Date: ").append(date).append("\n");
        info.append("Buyer: ").append(user).append("\n");
        info.append("Purchased products:\n");
        for (Product product : purchasedProducts) {
            info.append("- ").append(product).append("\n");
        }
        info.append("Total price: $").append(String.format("%.2f", totalPrice));
        return info.toString();
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPurchasedProducts(ArrayList<Product> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isVerification() {
        return verification;
    }

    public void setVerification(boolean verification) {
        this.verification = verification;
    }
}


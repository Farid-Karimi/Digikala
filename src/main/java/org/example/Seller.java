package org.example;

import java.util.ArrayList;

public class Seller extends Account{
    private ArrayList<Product> availableProducts;
    private double wallet;
    private boolean authorization;

    public Seller(String username, String password, double wallet) {
        super(username, password);
        this.availableProducts = new ArrayList<>();
        this.wallet = wallet;
        this.authorization = false;
    }
    public Seller(String username, String password) {
        super(username, password);
        this.availableProducts = new ArrayList<>();
        this.wallet = 0;
        this.authorization = false;
    }

    public ArrayList<Product> getAvailableProducts() {
        return availableProducts;
    }

    public void setAvailableProducts(ArrayList<Product> availableProducts) {
        this.availableProducts = availableProducts;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public boolean isAuthorization() {
        return authorization;
    }

    public void setAuthorization(boolean authorization) {
        this.authorization = authorization;
    }
    public void addProducts(Product product){
        availableProducts.add(product);
    }
}
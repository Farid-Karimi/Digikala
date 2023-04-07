package org.example;

import java.util.ArrayList;

public class Seller extends Account{

    private ArrayList<Product> availableProducts;
    private double wallet;

    public Seller(String username, String password, double wallet) {
        super(username, password);
        this.availableProducts = new ArrayList<>();
        this.wallet = wallet;
    }

    public Seller(String username, String password) {
        super(username, password);
        this.availableProducts = new ArrayList<>();
        this.wallet = 0;
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

    public void addProducts(Product product){
        product.setSeller(this);
        availableProducts.add(product);
    }

    public void depositToWallet(double amount){
        wallet += amount;
    }

    public void withdrawalFromWallet(double amount){
        wallet -= amount;
    }

    public String getInstance(){
        return "Seller";
    }

    public ArrayList<Product> findProductByName(String name){
        ArrayList<Product> result = new ArrayList<Product>();
        for (Product p : availableProducts) {
            if (p.getName().equalsIgnoreCase(name)) {
                result.add(p);
            }
        }
        return result;
    }

    public boolean doesProductExist(String name){
        for (Product p : availableProducts) {
            if (p.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("------------Seller Account------------\n");
        sb.append("Username: ").append(getUsername()).append("\n");
        sb.append("Password: ").append(getPassword()).append("\n");
        sb.append("Wallet balance: $").append(String.format("%.2f", wallet)).append("\n");
        sb.append("Authorization status: ").append(this.getAuthorization() ? "Authorized" : "Not authorized").append("\n");
        sb.append("Available products:\n");
        for (Product product : availableProducts) {
            sb.append("- ").append(product.getName()).append(", $").append(String.format("%.2f", product.getPrice()));
            sb.append(", quantity: ").append(product.getQuantity()).append("\n");
        }
        return sb.toString();
    }


}

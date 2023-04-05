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

    public void addProducts(Product product){
        product.setSeller(this);
        availableProducts.add(product);
    }

    public void depositToWallet(double amount){
        wallet += amount;
    }

    public void withdrawFromWallet(double amount){
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

    public void editExistingProduct(){

    }

}

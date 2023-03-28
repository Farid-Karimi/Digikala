package org.example;

import java.util.ArrayList;

public class User extends Account{
    private String email;
    private String phoneNumber;
    private String address;
    private double wallet;
    private ArrayList<Product> shoppingCart;
    private ArrayList<Product> orders;
    private ArrayList<Product> purchased;
    public User(String username, String password, String email, String phoneNumber, String address) {
        super(username, password);
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.wallet = 0;
        shoppingCart = new ArrayList<>();
        orders = new ArrayList<>();
        purchased = new ArrayList<>();
    }
    public User(String username, String password, String email, String phoneNumber, String address , double wallet) {
        super(username, password);
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.wallet = wallet;
        shoppingCart = new ArrayList<>();
        orders = new ArrayList<>();
        purchased = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public ArrayList<Product> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ArrayList<Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public ArrayList<Product> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Product> orders) {
        this.orders = orders;
    }

    public ArrayList<Product> getPurchased() {
        return purchased;
    }
    public void setPurchased(ArrayList<Product> purchased) {
        this.purchased = purchased;
    }
    public void addToCart(Product product){
        shoppingCart.add(product);
    }
    public void addToOrder(Product product){
        orders.add(product);
    }
    public void addToPurchased(Product product){
        purchased.add(product);
    }
    public void orderAllProducts(){
        orders.addAll(shoppingCart);
    }

}

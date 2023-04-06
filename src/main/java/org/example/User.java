package org.example;

import java.time.LocalDate;
import java.util.ArrayList;

public class User extends Account{

    private String email;
    private String phoneNumber;
    private String address;
    private double wallet;
    private ArrayList<Product> shoppingCart;
    private ArrayList<Order> orders;
    private ArrayList<Product> purchased;
    private boolean authorization;

    public User(String username, String password, String email, String phoneNumber, String address) {

        super(username, password);
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.wallet = 0;
        shoppingCart = new ArrayList<>();
        orders = new ArrayList<>();
        purchased = new ArrayList<>();
        this.authorization = false;

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

    public void depositToWallet(double amount){
        wallet += amount;
    }

    public void withdrawalFromWallet(double amount){
        wallet -= amount;
    }

    public ArrayList<Product> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ArrayList<Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
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

    public void addToOrder(Order order){
        orders.add(order);
    }

    public void addToPurchased(Product product){
        purchased.add(product);
    }

    public void viewCart() {
        if (shoppingCart.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
        }
        else {
            System.out.println("Shopping cart contents:");
            for (Product p : shoppingCart) {
                System.out.println(p);
            }
        }
    }

    public void removeFromCart(Product product) {
        shoppingCart.remove(product);
    }

    public void updateQuantity(Product product, int quantity) {
        product.setQuantity(quantity);
    }

    public void viewWalletBalance(){
        System.out.println("your balance is: ");
        System.out.println(wallet);
    }

    public void checkout(Shop shop) {
        Order order = new Order(LocalDate.now(), this, this.shoppingCart);
        shop.getOrderList().add(order);

        for (Product product : shoppingCart) {
            product.setQuantity(product.getQuantity() - 1);
            product.getSeller().depositToWallet(product.getPrice() * 0.9);
        }
        shoppingCart.clear();
        shop.addProfit(order.getTotalPrice() * 0.1);

        System.out.println("Order confirmed:\n" + order);
    }

    public String getInstance(){
        return "User";
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("------------USER INFORMATION------------\n");
        sb.append("Username:     ").append(getUsername()).append("\n");
        sb.append("Email:        ").append(email).append("\n");
        sb.append("Phone number: ").append(phoneNumber).append("\n");
        sb.append("Address:      ").append(address).append("\n");
        sb.append("Wallet:       ").append(String.format("$%.2f", wallet)).append("\n");
        sb.append("Authorization:").append(authorization ? "Yes" : "No").append("\n");
        return sb.toString();

    }
}

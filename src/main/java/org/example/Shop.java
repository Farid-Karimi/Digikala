package org.example;

import java.util.ArrayList;
import java.util.concurrent.TransferQueue;

public class Shop {

    private String name;
    private String webAddress;
    private String phoneNumber;
    private ArrayList<Account> accountList;
    private ArrayList<Product> productList;
    private ArrayList<Order> orderList;
    private ArrayList<Order> orderQueue;
    private ArrayList<Account> accountQueue;
    private ArrayList<Transfer> transferQueue;
    private double totalProfit;

    public Shop(String name, String webAddress, String phoneNumber, double totalProfit) {
        this.name = name;
        this.webAddress = webAddress;
        this.phoneNumber = phoneNumber;
        this.accountList = new ArrayList<>();
        this.productList = new ArrayList<>();
        this.orderList = new ArrayList<>();
        this.orderQueue = new ArrayList<>();
        this.accountQueue = new ArrayList<>();
        this.transferQueue = new ArrayList<>();
        this.totalProfit = totalProfit;
    }

    public ArrayList<Product> findProductByName(String name){
        ArrayList<Product> result = new ArrayList<Product>();
        for (Product p : productList) {
            if (p.getName().equalsIgnoreCase(name)) {
                result.add(p);
            }
        }
        return result;
    }

    public Account findAccountByName(String name){
        for (Account acc : accountList) {
            if (acc.getUsername().equalsIgnoreCase(name)) {
                return acc;
            }
        }
        return null;
    }

    public boolean doesAccountExist(String name){
        for (Account acc : accountList) {
            if (acc.getUsername().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean doesProductExist(String name){
        ArrayList<Product> result = new ArrayList<Product>();
        for (Product p : productList) {
            if (p.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(ArrayList<Account> accountList) {
        this.accountList = accountList;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public void addProduct(Product product){
        productList.add(product);
    }

    public void addOrder(Order order){
        orderList.add(order);
    }
    public void addToVerifyOrder(Order order){
        orderQueue.add(order);
    }

    public void addAccount(Account account){
        accountList.add(account);
    }
    public void addToVerifyAccount(Account account){
        accountQueue.add(account);
    }
    public void addToVerifyTransaction(Transfer transfer){
        transferQueue.add(transfer);
    }

    public ArrayList<Order> getOrderQueue() {
        return orderQueue;
    }

    public void setOrderQueue(ArrayList<Order> orderQueue) {
        this.orderQueue = orderQueue;
    }

    public ArrayList<Account> getAccountQueue() {
        return accountQueue;
    }

    public void setAccountQueue(ArrayList<Account> accountQueue) {
        this.accountQueue = accountQueue;
    }

    public ArrayList<Transfer> getTransferQueue() {
        return transferQueue;
    }

    public void setTransferQueue(ArrayList<Transfer> transferQueue) {
        this.transferQueue = transferQueue;
    }

    public void addProfit(double amount){
        totalProfit += amount;
    }

    public ArrayList<Order> findOrdersByAccount(Account account) {
        ArrayList<Order> matchingOrders = new ArrayList<>();
        for (Order order : orderList) {
            if (order.getUser().equals(account)) {
                matchingOrders.add(order);
            }
        }
        return matchingOrders;
    }

    public Account findAccountByEmail(String username) {
        for (Account account : accountList) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }

    public ArrayList<Product> findProductsByPriceRange(double minPrice, double maxPrice) {
        ArrayList<Product> matchingProducts = new ArrayList<>();
        for (Product product : productList) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }


}

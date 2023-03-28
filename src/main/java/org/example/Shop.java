package org.example;

import java.util.ArrayList;

public class Shop {
    private String name;
    private String webAddress;
    private String phoneNumber;
    private ArrayList<Account> accountList;
    private ArrayList<Product> productList;
    private ArrayList<Product> orderList;
    private double totalProfit;

    public Shop(String name, String webAddress, String phoneNumber, double totalProfit) {
        this.name = name;
        this.webAddress = webAddress;
        this.phoneNumber = phoneNumber;
        this.accountList = new ArrayList<>();
        this.productList = new ArrayList<>();
        this.orderList = new ArrayList<>();
        this.totalProfit = totalProfit;
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

    public ArrayList<Product> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Product> orderList) {
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
    public void addOrder(Product product){
        orderList.add(product);
    }
    public void addAccount(Account account){
        accountList.add(account);
    }

}

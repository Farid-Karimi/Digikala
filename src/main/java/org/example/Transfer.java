package org.example;

import java.time.LocalDate;

public class Transfer {

    private Seller seller;
    private User user;
    private double amount;
    private LocalDate date;
    private String depositOrWithdrawal;
    private boolean isUser;

    public Transfer(User user, double amount, LocalDate date, String depositOrWithdrawal){
        this.user = user;
        this.seller = null;
        this.amount = amount;
        this.date = date;
        this.depositOrWithdrawal = depositOrWithdrawal;
        this.isUser = true;
    }

    public Transfer(Seller seller, double amount, LocalDate date, String depositOrWithdrawal){
        this.seller = seller;
        this.user = null;
        this.amount = amount;
        this.date = date;
        this.depositOrWithdrawal = depositOrWithdrawal;
        this.isUser = false;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDepositOrWithdrawal() {
        return depositOrWithdrawal;
    }

    public void setDepositOrWithdrawal(String depositOrWithdrawal) {
        this.depositOrWithdrawal = depositOrWithdrawal;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TRANSFER\n");
        if (isUser){
            sb.append("Name:  ").append(user.getUsername()).append("\n");
        }
        else{
            sb.append("Name:  ").append(seller.getUsername()).append("\n");
        }
        sb.append("amount:  ").append(String.format("$%.2f", amount)).append("\n");
        sb.append("date: ").append(date).append("\n");
        if (isUser){
            sb.append("balance: ").append(user.getWallet()).append("\n");
        }
        else{
            sb.append("balance: ").append(seller.getWallet()).append("\n");
        }
        sb.append("Withdrawal / Deposit: ").append(depositOrWithdrawal).append("\n");
        return sb.toString();
    }
}

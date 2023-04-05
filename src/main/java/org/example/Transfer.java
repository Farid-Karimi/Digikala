package org.example;

import java.time.LocalDate;

public class Transfer {

    private Account account;
    private double amount;
    private LocalDate date;
    private String depositOrWithdrawal;

    public Transfer(Account account, double amount, LocalDate date, String depositOrWithdrawal){
        this.account = account;
        this.amount = amount;
        this.date = date;
        this.depositOrWithdrawal = depositOrWithdrawal;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public String isDepositOrWithdrawal() {
        return depositOrWithdrawal;
    }

    public void setDepositOrWithdrawal(String depositOrWithdraw) {
        this.depositOrWithdrawal = depositOrWithdrawal;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TRANSFER\n");
        sb.append("Name:      ").append(account.getUsername()).append("\n");
        sb.append("amount:     ").append(String.format("$%.2f", amount)).append("\n");
        sb.append("date:  ").append(date).append("\n");
        sb.append("Withdrawal / Deposit: ").append(depositOrWithdrawal).append("\n");
        return sb.toString();
    }
}

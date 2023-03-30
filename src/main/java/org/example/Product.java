package org.example;

import java.util.UUID;

public class Product {
    private UUID id;

    public String name;
    private double price;
    private double quantity;
    private String comment;
    private Seller seller;

    public Product(String name, double price, double quantity, String comment) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.comment = comment;
        this.seller = null;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String toString() {
        return String.format("%s: %s - $%.2f - Quantity: %d - Comment: %s", id.toString(), name, price, quantity, comment);
    }

}

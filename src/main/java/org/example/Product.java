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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PRODUCT\n");
        sb.append("ID:        ").append(id).append("\n");
        sb.append("Name:      ").append(name).append("\n");
        sb.append("Price:     ").append(String.format("$%.2f", price)).append("\n");
        sb.append("Quantity:  ").append(quantity).append("\n");
        sb.append("Comment:   ").append(comment).append("\n");
        sb.append("Seller:    ").append(seller.getUsername()).append("\n");
        return sb.toString();
    }


}

package org.example;

public class Product {
    public String name;
    private double price;
    private double quantity;
    private String comment;

    public Product(String name, double price, double quantity, String comment) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.comment = comment;
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
    public String toString() {
        return "Name: " + name + "\nPrice: $" + price + "\nQuantity: " + quantity + "\nComment: " + comment;
    }
}

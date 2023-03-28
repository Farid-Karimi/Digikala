package org.example;

public class Toy extends Product {
    private String brand;
    private String ageRange;

    public Toy(String name, double price, int quantity, String comment, String brand, String ageRange) {
        super(name, price, quantity, comment);
        this.brand = brand;
        this.ageRange = ageRange;
    }

    // getters and setters for instance variables

    public String toString() {
        return super.toString() + "\nBrand: " + brand + "\nAge Range: " + ageRange;
    }
}

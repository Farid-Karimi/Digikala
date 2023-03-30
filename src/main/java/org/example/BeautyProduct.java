package org.example;

public class BeautyProduct extends Product {

    private String brand;
    private String type;

    public BeautyProduct(String name, double price, int quantity, String comment, String brand, String type) {
        super(name, price, quantity, comment);
        this.brand = brand;
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return super.toString() + "\nBrand: " + brand + "\nType: " + type;
    }
}

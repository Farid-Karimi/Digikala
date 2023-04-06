package org.example;

public class Food extends Product {

    private String brand;
    private String expirationDate;

    public Food(String name, double price, int quantity, String comment, String brand, String expirationDate) {
        super(name, price, quantity, comment);
        this.brand = brand;
        this.expirationDate = expirationDate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Brand:     ").append(brand).append("\n");
        sb.append("Expiration Date: ").append(expirationDate).append("\n");
        return sb.toString();
    }
}

package org.example;

public class Toy extends Product {

    private String brand;
    private String ageRange;

    public Toy(String name, double price, int quantity, String comment, String brand, String ageRange) {
        super(name, price, quantity, comment);
        this.brand = brand;
        this.ageRange = ageRange;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Brand:     ").append(brand).append("\n");
        sb.append("Age Range: ").append(ageRange).append("\n");
        return sb.toString();
    }

    public Toy clone(int quantity) {
        Toy clonedProduct = new Toy(this.getName(), this.getPrice(), quantity, this.getComment(),this.brand, this.ageRange);
        return clonedProduct;
    }
}

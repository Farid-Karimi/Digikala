package org.example;

public class Electronics extends Product {

    private String brand;
    private String model;

    public Electronics(String name, double price, int quantity, String comment, String brand, String model) {
        super(name, price, quantity, comment);
        this.brand = brand;
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Brand:     ").append(brand).append("\n");
        sb.append("Model:     ").append(model).append("\n");
        return sb.toString();
    }

    public Electronics clone(int quantity) {
        Electronics clonedProduct = new Electronics(this.getName(), this.getPrice(), quantity, this.getComment(), this.brand, this.model);
        return clonedProduct;
    }

}

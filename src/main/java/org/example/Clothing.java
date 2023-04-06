package org.example;

public class Clothing extends Product {

    private String size;
    private String color;
    private String gender;

    public Clothing(String name, double price, double quantity, String comment, String size, String color, String gender) {
        super(name, price, quantity, comment);
        this.size = size;
        this.color = color;
        this.gender = gender;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Size:      ").append(size).append("\n");
        sb.append("Color:     ").append(color).append("\n");
        return sb.toString();
    }
}

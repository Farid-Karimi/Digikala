package org.example;

public class Laptop extends Electronics {

    private String screenSize;
    private String operatingSystem;
    private String CPU;
    private String GPU;


    public Laptop(String name, double price, int quantity, String comment, String brand, String model, String screenSize, String operatingSystem, String CPU, String GPU) {
        super(name, price, quantity, comment, brand, model);
        this.screenSize = screenSize;
        this.operatingSystem = operatingSystem;
        this.CPU = CPU;
        this.GPU = GPU;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getGPU() {
        return GPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Screen Size: ").append(screenSize).append("\n");
        sb.append("Operating System: ").append(operatingSystem).append("\n");
        sb.append("graphic card type: ").append(GPU).append("\n");
        sb.append("Processor Type:").append(CPU).append("\n");
        return sb.toString();

    }

    public Laptop clone(int quantity) {
        Laptop clonedProduct = new Laptop(this.getName(), this.getPrice(), quantity, this.getComment(), this.getBrand(), this.getModel(), this.screenSize, this.operatingSystem, this.CPU, this.GPU);
        return clonedProduct;
    }

}

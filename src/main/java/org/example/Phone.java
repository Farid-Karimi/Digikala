package org.example;

public class Phone extends Electronics {

    private String screenSize;
    private String operatingSystem;
    private String cameraSpecs;
    private int memory;

    public Phone(String name, double price, int quantity, String comment, String brand, String model, String screenSize, String operatingSystem, String cameraSpecs, int memory) {
        super(name, price, quantity, comment, brand, model);
        this.screenSize = screenSize;
        this.operatingSystem = operatingSystem;
        this.cameraSpecs = cameraSpecs;
        this.memory = memory;
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

    public String getCameraSpecs() {
        return cameraSpecs;
    }

    public void setCameraSpecs(String cameraSpecs) {
        this.cameraSpecs = cameraSpecs;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Screen Size: ").append(screenSize).append("\n");
        sb.append("Operating System: ").append(operatingSystem).append("\n");
        return sb.toString();
    }

    public Phone clone(int quantity) {
        Phone clonedProduct = new Phone(this.getName(), this.getPrice(), quantity, this.getComment(), this.getBrand(), this.getModel(), this.screenSize, this.operatingSystem, this.cameraSpecs, this.memory);
        return clonedProduct;
    }
}

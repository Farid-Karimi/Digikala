package org.example;

public class Headphone extends Electronics {

    private String connectionType;
    private String batteryCapacity;

    public Headphone(String name, double price, int quantity, String comment, String brand, String model, String connectionType, String batteryCapacity) {
        super(name, price, quantity, comment, brand, model);
        this.connectionType = connectionType;
        this.batteryCapacity = batteryCapacity;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public String getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(String batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Connection Type: ").append(connectionType).append("\n");
        sb.append("Noise Cancellation: ").append(batteryCapacity).append("\n");
        return sb.toString();
    }

    public Headphone clone(int quantity) {
        Headphone clonedProduct = new Headphone(this.getName(), this.getPrice(), quantity, this.getComment(), this.getBrand(), this.getModel(), this.connectionType, this.batteryCapacity);
        return clonedProduct;
    }
}


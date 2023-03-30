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
        return super.toString() + "\nConnection Type: " + connectionType + "\nNoise Cancellation: " + batteryCapacity;
    }
}


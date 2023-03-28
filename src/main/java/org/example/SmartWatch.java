package org.example;

public class SmartWatch extends Electronics{
    private String bandType;
    private String buildMaterial;
    private String screenSize;

    public SmartWatch(String name, double price, int quantity, String comment, String brand, String model, String bandType, String buildMaterial, String screenSize) {
        super(name, price, quantity, comment, brand, model);
        this.bandType = bandType;
        this.buildMaterial = buildMaterial;
        this.screenSize = screenSize;
    }

    public String getBandType() {
        return bandType;
    }

    public void setBandType(String bandType) {
        this.bandType = bandType;
    }

    public String getBuildMaterial() {
        return buildMaterial;
    }

    public void setBuildMaterial(String buildMaterial) {
        this.buildMaterial = buildMaterial;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String toString() {
        return super.toString() + "\nBand Type: " + bandType + "\nConnectivity: " + buildMaterial;
    }
}

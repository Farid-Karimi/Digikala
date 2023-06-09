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
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Band Type: ").append(bandType).append("\n");
        sb.append("Connectivity: ").append(buildMaterial).append("\n");
        return sb.toString();
    }

    public SmartWatch clone(int quantity) {
        SmartWatch clonedProduct = new SmartWatch(this.getName(), this.getPrice(), quantity, this.getComment(), this.getBrand(), this.getModel(), this.bandType, this.buildMaterial, this.screenSize);
        return clonedProduct;
    }
}

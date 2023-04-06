package org.example;

public class test {
    public static void main(String[] args) {
        Electronics nene = new Electronics("1",11,111,"1111","11111","111111");
        Product sasa = new Product("1",11,111,"1111");
        Seller seller = new Seller("111","111");
        nene.setSeller(seller);
        System.out.println(nene);
    }
}
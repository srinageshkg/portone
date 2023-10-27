package com.dcp.portone.leet.absint;

public class FurnitureObject extends ProductForSale{
    public FurnitureObject(String type, double price, String desc) {
        super(type, price, desc);
    }

    @Override
    public void showDetails() {
        System.out.printf("Type: %-25s ", type);
        System.out.println("\t\tFurnitureProduct: "+desc);
        System.out.println("\t\tPrice: "+price);
    }

}

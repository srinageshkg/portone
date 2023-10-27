package com.dcp.portone.leet.absint;

public abstract class ProductForSale {
    protected String type;
    protected double price;
    protected String desc;

    public double getSalesPrice(int quantity) {
        return quantity * price;
    }

    public void printPricedLineItem(int quantity) {
        System.out.printf(": %-2d qty at $%-8.2f each, %-15s, %-35s %n", quantity, price, type, desc);
    }

    public ProductForSale(String type, double price, String desc) {
        this.type = type;
        this.price = price;
        this.desc = desc;
    }

    public abstract void showDetails();
}

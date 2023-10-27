package com.dcp.portone.leet.absint;

public class Horse extends Mammal {
    public Horse(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void shedHair() {
        System.out.println(getExplicitType() + "Sheds in the spring!");
    }

    @Override
    public void makeNoise() {

    }
}

package com.dcp.portone.leet.absint;

public class Dog extends Mammal {


    public Dog(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void move(String speed) {
        if (speed.equalsIgnoreCase("slow")) {
            System.out.println(getExplicitType() + " walking.");
        } else {
            System.out.println(getExplicitType() + " running.");
        }
    }

    @Override
    public void shedHair() {
        System.out.printf(getExplicitType() + " sheds hair all the time.%n");
    }

    @Override
    public void makeNoise() {
        if (type == "Wolf") {
            System.out.print("Howling! ");
        } else {
            System.out.print("Woof! ");
        }
    }
}

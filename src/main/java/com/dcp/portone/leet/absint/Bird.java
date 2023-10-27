package com.dcp.portone.leet.absint;

public class Bird extends Animal implements FlightEnabled, Trackable {

    public Bird(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void move(String speed) {
        System.out.println("Flying");
    }

    @Override
    public void makeNoise() {
        System.out.println("Chirp..");
    }

    @Override
    public void takeOff() {
        System.out.println(getClass().getSimpleName() + " is taking off...");
    }

    @Override
    public void land() {
        System.out.println(getClass().getSimpleName() + " is landing...");

    }

    @Override
    public void fly() {
        System.out.println(getClass().getSimpleName() + " is flying...");

    }

    @Override
    public void track() {
        System.out.println(getClass().getSimpleName() + "'s coordinates recorded");
    }
}


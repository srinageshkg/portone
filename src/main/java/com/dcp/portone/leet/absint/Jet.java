package com.dcp.portone.leet.absint;

public class Jet implements FlightEnabled, Trackable{

    public Jet() {
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
    public FlightStages transition(FlightStages stage) {
        System.out.println(getClass().getSimpleName() + " transitioning");
        return FlightEnabled.super.transition(stage);
    }

    @Override
    public void track() {
        System.out.println(getClass().getSimpleName() + "'s coordinates recorded");
    }


}

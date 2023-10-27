package com.dcp.portone.leet.absint;

public class Truck implements Trackable{

    public Truck() {
    }
    @Override
    public void track() {
        System.out.println(getClass().getSimpleName() + "'s coordinates recorded");
    }
}

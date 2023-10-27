package com.dcp.portone.leet.absint;

/*
public interface Comparaboe<T> {
    int compareTo(T o);
}

*/
abstract class Mammal extends Animal {
    public Mammal(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void move(String speed) {
        System.out.print(getExplicitType() + " ");
        System.out.print(speed.equalsIgnoreCase("Slow") ? "Walks" : "runs");
    }

    public abstract void shedHair();
}

enum FlightStages implements Trackable {GROUNDED, LAUNCH, CRUISE, DATA_COLLECTION;

    @Override
    public void track() {
        if (this != GROUNDED) {
            System.out.println("Monitoring " + this);
        }
    }

    public FlightStages getNextStage() {
        FlightStages[] allStages = values();
        return allStages[(ordinal()+1) % allStages.length];
    }
}
record DragonFLy(String name, String type) implements FlightEnabled {
    // Abstracted Types - CODING TO AN INTERFACE ??????
    @Override
    public void takeOff() {

    }

    @Override
    public void land() {

    }

    @Override
    public void fly() {

    }
}
class Satellite implements OrbitEarth {
    FlightStages stage = FlightStages.GROUNDED;
    public Satellite() {
    }

    @Override
    public void achiveOrbit() {
        transition("Orbit Achieved.");
    }

    @Override
    public void takeOff() {
        transition("Taking off");
    }

    @Override
    public void land() {
        transition("Landing");

    }

    @Override
    public void fly() {
        achiveOrbit();
        transition("Data Collection while Orbiting");

    }

    public void transition(String desc) {
        System.out.println("desc = " + desc);
        stage = transition(stage);
        stage.track();
    }
}

interface OrbitEarth extends FlightEnabled {
    void achiveOrbit();

    private static void log(String description) {
        var today = new java.util.Date();
        System.out.println(today + ": " + description);
    }
    private static void logStage(FlightStages stage, String description) {
        description = stage + ": " + description;
        log(description);
    }

    @Override
    default FlightStages transition(FlightStages stage) {
        FlightStages nextStage = FlightEnabled.super.transition(stage);
        System.out.println(stage + " beginning transition to " + nextStage);
        return nextStage;
    }
}

interface FlightEnabled {
    // These are not instance fields. They are public static and final  --i.e constants
    public static final double MILES_TO_KMS = 1.609;
    double KMS_TO_MILES = 0.621;
    public abstract void takeOff();
    abstract void land();
    void fly();

    default FlightStages transition(FlightStages stage) {
        FlightStages nextStage = stage.getNextStage();
        System.out.println("Transitioning from " + stage + " to " + nextStage);
        return nextStage;
    }
}
interface Trackable {
    void track();
}

public abstract class Animal {
    protected String type;
    protected String size;
    protected double weight;

    public Animal(String type, String size, double weight) {
        this.type = type;
        this.size = size;
        this.weight = weight;
    }

    public abstract void move(String speed);
    public abstract void makeNoise();

    public final String getExplicitType() {
        return getClass().getSimpleName() + " (" + type + ")" + " (" + ")";
    }
}

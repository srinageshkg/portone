package com.dcp.portone.leet.absint;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Animal dog = new Dog("Wolf", "big", 100);
        //dog.makeNoise();

        //doAnimalStuff(dog);
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(dog);
        animals.add(new Dog("German Shepard", "big", 150));
        animals.add(new Fish("Goldfish", "small", 1));
        animals.add(new Fish("Barrakuda", "big", 75));
        animals.add(new Dog("Pug", "small", 20));
        animals.add(new Horse("Clydesdale", "large", 1000));
        Bird bird = new Bird("Pitta", "small", 1);
        Animal bbird = new Bird("Palkan", "big", 60);
        FlightEnabled flyer = new Bird("Konga", "small", 3);
        Trackable trabird = new Bird("FarPita", "big", 20);
        animals.addAll(List.of(bird, bbird));
//        animals.addAll(List.of(bird, bbird, filer, trabird));
        animals.forEach(Main::doAnimalStuff);
        //animals.forEach(a -> doAnimalStuff(a));
        //for (Animal animal : animals) doAnimalStuff(animal);
/*        flyer.takeOff();
        flyer.fly();
        flyer.land();
        trabird.track();*/

        inFlight(flyer);
        inFlight(new Jet());

        Truck truck = new Truck();
        truck.track();

        double kmsTravellled = 100;
        double milesTravelled = kmsTravellled * FlightEnabled.KMS_TO_MILES;
        System.out.println("Truck Travelled Miles = " + milesTravelled);
        System.out.println("Truck Travelled Kms = " + kmsTravellled);
    }

    private static void doAnimalStuff(Animal animal) {
        animal.makeNoise();
        animal.move("Fast");
        if (animal instanceof Mammal curMammal) {
            curMammal.shedHair();
        }
    }

    public static void inFlight(FlightEnabled flier) {
        flier.takeOff();
        flier.fly();
        if (flier instanceof Trackable tracked) {
            tracked.track();
        }
        flier.land();
    }
}

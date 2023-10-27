package com.dcp.portone.polymor;
class mainCar {
    public static void main(String[] args) {
        Car car = new Car("Honda Civic");

        runRace(car);

        System.out.println("car.getAdjustedPrice(\"SMALL\") = " + car.getAdjustedPrice("SMALL"));
    }

    public static void runRace(Car car) {
        car.startEngine();
    }
}
class Car {
    private String desc;

    public Car(String desc) {
        this.desc = desc;
    }

    public void startEngine() {
        System.out.println("Car Engine Starting...");
    }

    public double getAdjustedPrice(String size){
        double basePrice = 3;
        return switch (size) {
            case "SMALL", "MEDIUM" -> basePrice - 1.5;
            case "LARGE" -> basePrice + 1;
            default -> basePrice;
        };
    }
}


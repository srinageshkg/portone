package com.dcp.ifs;

public class Test {
    public static void main(String[] args) {
/*        try {
            main(args);
        } catch (StackOverflowError error) {
//            throw new StackOverflowError("Don't call main within main...");
            System.out.println("StackOverflowError ");
        } catch (Exception e) {
            System.out.println("e CATCH = " + e);
        }*/
        System.out.println("  OUT... ");

        Ani myD = new Doggy();
        Ani muDog = new Doggy("Lovely", "Spec");
        muDog.getName();
        ((Doggy) muDog).getBreed();

        int i = '5';
    }

}

abstract class Ani {
    private String name;
    Ani() {}
    Ani(String name) {
        //super();
        this.name=name;
    }
    public void getName() {
        System.out.println(name);;
    }
}

class Doggy extends Ani {
    private String breed;
    Doggy() {
        this(null);
    }
    Doggy(String breed) {
        //super();
        this.breed = breed;
    }
    Doggy(String name, String breed) {
        super(name);
        this.breed = breed;
    }

    public void getBreed() {
        System.out.println(breed);
    }
}
class Stk {
    public void print() {

    }
}
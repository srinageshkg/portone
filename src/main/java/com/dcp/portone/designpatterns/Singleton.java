package com.dcp.portone.designpatterns;

public class Singleton {
    // Decalre variable
    private Integer money = 1000;
    private Singleton() {}
    private static Singleton singleton = null;
    public static Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
    public Integer getBalance() {
        return this.money;
    }
    public Integer getMoney(Integer amt) {
        if (money >= amt)
            money -= amt;
        return money;
    }
    public Integer addMoney(Integer amt) {
        money += amt;
        return money;
    }
}

class SingletonSide {
    public static void main(String[] args) {

        Singleton singletontest = Singleton.getInstance();

        System.out.println(singletontest.getMoney(100) + " Balance after 100 debit." + singletontest.getBalance());
        String formatted = "%d is Balance after %d Credit.".formatted(Singleton.getInstance().addMoney(500), 500);
        System.out.println(formatted);
        System.out.println(singletontest.getMoney(200)+ " Balance.after 200 debit");

        Singleton singletonOne = Singleton.getInstance();
        Singleton singletonTwo = Singleton.getInstance();
        System.out.println(singletonOne.addMoney(500));
        System.out.println(singletonTwo.getMoney(200));
    }
}

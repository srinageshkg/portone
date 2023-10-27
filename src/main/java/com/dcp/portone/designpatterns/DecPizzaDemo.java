package com.dcp.portone.designpatterns;
public class DecPizzaDemo {

    public static void main(String[] args) {
        DecPizzaDemo decPizzaDemo = new DecPizzaDemo();
        decPizzaDemo.DecDemo();
    }

    public void DecDemo(){
        DecPizza pizza = new DecSimplyVegPizza();
        System.out.println(pizza.toString());
        pizza = new DecBroccoli(pizza);
        System.out.println(pizza.toString());
        pizza = new DecCheese(pizza);
        System.out.println(pizza.toString());

        System.out.println(pizza.getDesc());
        System.out.println(pizza.getPrice());

        DecPizza pizzanonveg = new DecSimplyNonVegPizza();
        System.out.println("pizzanonveg = " + pizzanonveg.toString());

        pizzanonveg = new DecChicken(pizzanonveg);
        System.out.println("pizzanonveg = " + pizzanonveg.toString());



    }
}

class DecChicken extends DecPizzaDecorator {
    private final DecPizza pizza;
    public DecChicken(DecPizza pizza){
        this.pizza = pizza;
    }
    private Double price=25.0D;
    private String desc="DecChicken-SimplyNonVegPizza (25.00)"+ price + "<>";
    @Override
    public String getDesc(){
        return this.desc;
    }
    @Override
    public Double getPrice(){
        return pizza.getPrice()+this.price;
    }
    @Override
    public String toString() {
        return "DecChicken %s %10.2f %s".formatted(pizza, price, desc);
    }
}
class DecCheese extends DecPizzaDecorator {
    private final DecPizza pizza;
    public DecCheese(DecPizza pizza){
        this.pizza = pizza;
    }
    private Double price=11.0D;
    private String desc="DecCheese-SimplyVegPizza (11.00)"+ price + "<>";
    @Override
    public String getDesc(){
        return this.desc;
    }
    @Override
    public Double getPrice(){
        return pizza.getPrice()+this.price;
    }

    @Override
    public String toString() {
        return "DecCheese %s %10.2f %s".formatted(pizza, price, desc);
    }
}
class DecBroccoli extends DecPizzaDecorator{
    private final DecPizza pizza;
    public DecBroccoli(DecPizza pizza){
        this.pizza = pizza;
    }
    private Double price=3.0D;
    private String desc="DecBroccoliSimplyVegPizza (3.00)" + price + "<>";
    @Override
    public String getDesc(){
        return this.desc;
    }
    @Override
    public Double getPrice(){
        return pizza.getPrice()+this.price;
    }

    @Override
    public String toString() {
        return "DecBroccoli %s %10.2f %s".formatted(pizza, price, desc);
    }
}

abstract class DecPizzaDecorator implements DecPizza{
    @Override
    public String getDesc(){
        return "Toppings";
    }
    @Override
    public Double getPrice(){
        return null;
    }
}
class DecSimplyVegPizza implements DecPizza{
    private Double price=5.50D;
    private String desc="SimplyVegPizza (5.50)" + price + "<>";
    @Override
    public String getDesc(){
        return this.desc;
    }
    @Override
    public Double getPrice(){
        return this.price;
    }
    @Override
    public String toString() {
        return "DecSimplyVegPizza %10.2f %s".formatted(price, desc);
    }
}

class SimplyNonVegPizza implements DecPizza {
    private Double price = 9.50D;
    private String desc = "SimplyNonVegPizza (9.50)"+ price + "<>";

    @Override
    public String getDesc() {
        return this.desc;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }
}
class DecSimplyNonVegPizza implements DecPizza{
    private Double price=12.50D;
    private String desc="SimplyNonVegPizza (12.50)"+ price + "<>";
    @Override
    public String getDesc(){
        return this.desc;
    }
    @Override
    public Double getPrice(){
        return this.price;
    }
    @Override
    public String toString() {
        return "DecSimplyNonVegPizza %10.2f %s".formatted(price, desc);
    }
}

interface DecPizza {
    public String getDesc();
    public Double getPrice();
}
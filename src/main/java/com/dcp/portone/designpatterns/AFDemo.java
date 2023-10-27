package com.dcp.portone.designpatterns;

public class AFDemo {
    public static void main(String[] args) {
        AFDemo asd = new AFDemo();
        asd.demo();
    }
    public void demo(){
        //AFFactoryProducer.getFactory(true).getShape("ROUNDEDSQUARE").draw();;

        AFAbstractFactory afAbstractFactory = AFFactoryProducer.getFactory(false);

        AFShape afShape1 = afAbstractFactory.getShape("RECTANGLE");
        afShape1.draw();
        AFShape afShape2 = afAbstractFactory.getShape("SQUARE");
        afShape2.draw();

        AFAbstractFactory afrAbstractFactory = AFFactoryProducer.getFactory(true);

        AFShape afShape3 = afrAbstractFactory.getShape("ROUNDEDRECTANGLE");
        afShape3.draw();
        AFShape afShape4 = afrAbstractFactory.getShape("ROUNDEDSQUARE");
        afShape4.draw();
    }
}

class AFFactoryProducer {
    public static AFAbstractFactory getFactory(boolean rounded) {
        if (rounded) {
            return new AFRoundedShapeFactory();
        } else {
            return new AFSquareShapeFactory();
        }
    }
}
class AFRoundedShapeFactory extends AFAbstractFactory{
    @Override
    public AFShape getShape(String type) {
        if(type.equalsIgnoreCase("ROUNDEDRECTANGLE")){
            return new AFRoundedRectangle();
        } else if (type.equalsIgnoreCase("ROUNDEDSQUARE")) {
            return new AFRoundedSquare();
        }
        return null;
    }
}
class AFSquareShapeFactory extends AFAbstractFactory{
    @Override
    public AFShape getShape(String type) {
//        if(type.equalsIgnoreCase("RECTANGLE")){
        if("RECTANGLE".equalsIgnoreCase(type)){
            return new AFRectangle();
        } else if ("SQUARE".equalsIgnoreCase(type)) {
            return new AFSquare();
        }
        return null;
    }
}
abstract class AFAbstractFactory {
    abstract AFShape getShape(String type);
}
class AFRoundedRectangle implements AFShape{
    private final String shape = new String("Inside AFRoundedRectangle::draw() method.");
    @Override
    public String draw(){
        System.out.println(shape);
        return (this.shape);
    }
}
class AFRoundedSquare implements AFShape{
    private final String shape = new String("Inside AFRoundedSquare::draw() method.");
    @Override
    public String draw(){
        System.out.println(shape);
        return (this.shape);
    }
}
class AFSquare implements AFShape {
    private final String shape = new String("Inside AFSquare::draw() method.");
    @Override
    public String draw(){
        System.out.println(shape);
        return (this.shape);
    }
}
class AFRectangle implements AFShape{
    private final String shape = new String("Inside AFRectangle::draw() method.");
    @Override
    public String draw(){
        System.out.println(shape);
        return (this.shape);
    }
}
interface AFShape {
    public default String draw() {
        return "Default Implementation...";
    }
}

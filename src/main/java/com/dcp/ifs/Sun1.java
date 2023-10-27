package com.dcp.ifs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

// StackOverflowError is a subclass of Error type and not Exception type, hence it is not handled
// ClassCastException extends RuntimeException (unchecked exception)
// FileNotFoundException extends IOException, IOException extends Exception (checked exception),
// public class java.lang.Error extends Throwable
// ExceptionInInitializerError is from Error family and is thrown by a static initializer block
// str.equals(sb) => String class overrides equals(Object) method but as "sb" is of StringBuilder type so this returns false.
// StringBuilder class doesn't override equals(Object) method. So Object version is invoked which uses == operator,
// equals(Object) method defined in Object class uses == operator to check the equality
// String and StringBuilder classes override toString() method, which prints the text stored in these classes
// Java compiler adds super(); as the first statement inside constructor, ....
// ...if call to another constructor using this(...) or super(...) is not available.
// String s2 = "OCAJP" + "";  (String pool) CONSTANT expression - and String s2 = s1 + ""; IS NOT


public class Sun1 {
    static {
        try {
            System.out.println(1 / 0);
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic Exception handled...");
        } catch (ExceptionInInitializerError e) {
            System.out.println("OK RESOLVED>>> ");
        }
    }
    public static void main(String[] args) {
        //short [] args = new short[]{50, 50};
        //System.out.println("HELLO");
        try {
            //main(args);   // StackOverFlowError
        } catch (Exception ex) {
            System.out.println("CATCH-");
        }
        System.out.println("OUT");
        StringBuilder sb = new StringBuilder(100);
        //System.out.println(sb.append(null).length());   // reference to append is ambiguous compilation error
        System.out.println(sb.length() + ":" + sb.toString().length());
        int grade = 60;
        if(grade == 60)   // if(grade = 60) compilation error incompatible types: int cannot be converted to boolean
            System.out.println("You passed...");
        else
            System.out.println("You failed...");

        Super su = new Sub();
        try {  // unreported exception java.io.IOException; must be caught or declared to be thrown
            su.m1();
        } catch (FileNotFoundException e) {
            System.out.print("M");
        } catch (IOException e) {   //
            throw new RuntimeException(e);//
        } finally {//==
            System.out.print("N");
        }
        System.out.println();
        LocalDateTime objLO = LocalDateTime.now();
        System.out.println(objLO.getSecond());

        LocalTime time = LocalTime.of(16, 40);
        String amPm = time.getHour() >= 12 ? (time.getHour() == 12) ? "PM" : "AM" : "PM"; //: expected
        System.out.println(amPm);

        int [] a1 = new int[8];
        int [] a12 [] = new int[8][];
        int a13[][] = new int[8][];    // new int[][8] ']' expected  new int[][] array dimention missing
        int [][] a114 = new int[8][8];

        /*INSERT*/ Character var = '7';   // Integer var = 7 int var = 7 char var = 7
        switch (var) { // switch can accept primitive types: byte, short, int, char; wrapper types: Byte, Short, Integer, Character; String and enums.
            case 7 -> System.out.println("Lucky no. 7");
            case 55 -> System.out.println("Lucky no. " + var);
            default -> System.out.println(Integer.valueOf(var) + "  " + var.charValue() + " DEFAULT");
        }

        List<String> l = new ArrayList<String>();
        String s = new String("Hello");
        List<String> list = new ArrayList<>();
        list.add(s);
        list.add(new String("Hello"));
        list.add(s);
        s = s.replace("l", "L");
        // System.out.println(s.replace("l", "L"));  // returns a new String

        System.out.println(list);

        LocalDate ldt = LocalDate.of(2023,9,12);
        Period pd = Period.ofMonths(2);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        System.out.println(dtf.format(ldt.minus(pd)));

        Predicate<String> pred = (p -> p.toUpperCase().substring(0,1).equals("A"));

        String bankcode = "ICICINBBRT4";
        System.out.println("Ccode = " + bankcode.substring(4,6));
        String str = "java";
        StringBuilder sbstr = new StringBuilder("java");

        System.out.println(str.equals(sbstr) + ":" + sbstr.equals(str));
        LocalDate date = LocalDate.of(2012, 1, 11);
        Period period = Period.ofMonths(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yy");
        System.out.print(formatter.format(date.minus(period)));
        System.out.println();
        String [] arr = {"A", "ab", "bab", "Aa", "bb", "baba", "aba", "Abab"};
        Predicate<String> p = sl -> sl.toUpperCase().substring(0,1).equals("A");
        processStringArray(arr, p);

        List<Student12> students = new ArrayList<>();
        Student12 s12 = new Student12("James", 25);
        students.add(s12);
        students.add(new Student12("James", 27));
        students.add(new Student12("James", 25));
        students.add(new Student12("James", 25));

        students.remove(new Student12("James", 25));
        //students.remove(s12);
        for(Student12 stud : students) {
            System.out.println(stud);
        }
        Object [] arrO = new Object[4];
        for(int i = 1; i <=3; i++) {
            switch(i) {
                case 1:
                    arrO[i] = new String("Java");
                    break;
                case 2:
                    arrO[i] = new StringBuilder("Java");
                    break;
                case 3:
                    arrO[i] = new SpecialString("Java");
                    break;
            }
        }
        for(Object obj : arrO) {
            System.out.println(obj);
        }
        short [] sargs = new short[]{50, 50};
        System.out.println("[" + sargs[0] + ", " + sargs[1] + "]");
        sargs[0] = 5;  // 35454 incompatible types: possible lossy conversion from int to short
        sargs[1] = 10;
        System.out.println("[" + sargs[0] + ", " + sargs[1] + "]");
        new BB();
        BB obj2 = new BB();
        AA obj1 = (AA)obj2;
        obj1.print();

        //System.out.println("Output is: " + 10 != 5);  // Compilation error bad operand types for binary operator '!='
        /*INSERT*/
//        short[] arrsi = new short[2]{5,10};
        int [] arrsi = new int []{0,0,0,0};
//        short arrsi [] = new short[3];
//        byte arrsi [] = new byte[3];
        arrsi[1] = 5;
        arrsi[2] = 10;
        System.out.println("[" + arrsi[1] + ", " + arrsi[2] + "]"); //Line n1

        DogAA dog1 = new DogAA("Beagle");
        DogAA dog2 = new DogAA("Bubbly", "Poodle");
        System.out.println(dog1.getName() + ":" + dog1.getBreed() + ":" +
                dog2.getName() + ":" + dog2.getBreed());
        int i = '5';
        m(i);
        m('5');
        System.out.println("i = " + i);

        List<Integer> li = new ArrayList<Integer>();
        li.add(2);
        li.add(1);
        li.add(0);

        li.remove(li.indexOf(1));

        System.out.println(li);
        String fruit = "mango";
        switch (fruit) {
            default:
                System.out.println("ANY FRUIT WILL DO");
            case "Apple":
                System.out.println("APPLE");
            case "Mango":
                System.out.println("MANGO");
            case "Banana":
                System.out.println("BANANA");
                break;
        }
        Point p1 = new Point();
        p1.x = 10;
        p1.y = 20;
        Point p2 = new Point();
        p2.assign(p1.x, p1.y);
        System.out.println(p1.toString() + ";" + p2.toString());

        int a = 100;
        System.out.println(-a++);
        String s1 = "OCAJP";
        String s2 = "OCAJP" + "";
        System.out.println(s1 == s2);
        do {
            System.out.println(100);
        } while (false);
        System.out.println("Bye");
        LocalDate newYear = LocalDate.of(2018, 1, 1);
        LocalDate christmas = LocalDate.of(2018, 12, 25);
        boolean flag1 = newYear.isAfter(christmas);
        boolean flag2 = newYear.isBefore(christmas);
        System.out.println(flag1 + ":" + flag2);
        //LocalDate datec = LocalDate.of(2020, 9, 31);  // 31 Exception thrown at runtime
        //System.out.println(datec);
        List<LocalDate> dates = new ArrayList<>();
        dates.add(LocalDate.parse("2018-07-11"));
        dates.add(LocalDate.parse("1919-02-25"));
        dates.add(LocalDate.of(2020, 4, 8));
        dates.add(LocalDate.of(1980, Month.DECEMBER, 31));

        dates.removeIf(x -> x.getYear() < 2000);

        System.out.println(dates);
        int [] ar = {2, 1, 0};
        for(int ii : ar) {
            System.out.println(ar[ii]);
        }

        String [] arrin = {"I", "N", "S", "E", "R", "T"};
        System.out.println(arrin.length);
        for(int n=0; n<=arrin.length; n+=1) {
            if (n % 2 == 0) {
                continue;
            }
            System.out.print(arrin[n]); //Line n1
        }
        List<Integer> listr = new ArrayList<>();
        listr.add(100);
        listr.add(200);
        listr.add(100);
        listr.add(200);
        listr.remove(listr.indexOf(100));

        System.out.println(listr);
        Period period1 = Period.of(0, 0, 0);
        System.out.println(period1);
//        byte var = 100;
        Double [] arr111 = new Double[2];
        //System.out.println(arr111[0] + arr111[1]);

        Message obj = new Message(); //Line n1
        obj.print(); //Line n2
        obj = change(obj); //Line n3
       //obj.setMsg("Happy Holidays!");
        obj.print(); //Line n4

        new BsuperThis().print();

    }
    public static Message change(Message m) { //Line n5
        m = new Message(); //Line n6
        m.msg = "Happy Holidays!"; //Line n7
        return m;

    }
    private static void processStringArray(String [] arr, Predicate<String> predicate) {
        for(String str : arr) {
            if(predicate.test(str)) {
                System.out.println(str);
            }
        }
    }
    private static void m(int x) {
        System.out.println("int version");
    }

    private static void m(char x) {
        System.out.println("char version");
    }
}
class Message {
    String msg = "Happy New Year!";
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public void print() {
        System.out.println(msg);
    }
}

class Point {
    int x;
    int y;
    void assign(int x, int y) {
        x = this.x;
        this.y = y;
    }

    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }
}
class AsuperThis {
    public int i1=1;
    protected int i2=2;
}
class BsuperThis extends AsuperThis {
    public void print() {
        AsuperThis asu = new AsuperThis();
        System.out.println("asu.i1 : " + asu.i1);
        System.out.println("asu.i2 : " + asu.i2);
        System.out.println("this.i2 : " + this.i2);
        System.out.println("super.i2 : " + super.i2);
    }
}
abstract class AnimalAA {
    private String name;
    AnimalAA() {}
    AnimalAA(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
class DogAA extends AnimalAA {
    private String breed;
    DogAA(String breed) {
        //super();
        //super(breed);
        this.breed = breed;
    }

    DogAA(String name, String breed) {
        super(name);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }
}
class AA {
    public void print() {
        System.out.println("AA");
    }
}
class BB extends AA {
    public void print() {
        System.out.println("BB");
    }
}
class SpecialString {
    String str;
    SpecialString(String str) {
        this.str = str;
    }
}
class Student12 {
    private String name;
    private int age;

    Student12(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "Student12[" + name + ", " + age + "]";
    }
}
interface Printable {
    public void setMargin();
    public void setOrientation();
}
abstract class Paper implements Printable { //Line 7
    public void setMargin() {}
    //Line 9
}
class NewsPaper extends Paper { //Line 12
    public void setMargin() {}
    public void setOrientation() {}
    //Line 14
}
abstract class Super {
    public abstract void m1() throws IOException;
}

class Sub extends Super {
    @Override
    public void m1() throws IOException {
        throw new FileNotFoundException();
    }
}

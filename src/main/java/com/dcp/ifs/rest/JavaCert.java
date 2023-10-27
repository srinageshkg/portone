package com.dcp.ifs.rest;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class JavaCert {
    public static void main(String[] args) throws Exception {
        //new LogHelper().help();
        List<Integer> list = new ArrayList<>();
        list.add(Integer.valueOf(100));
        list.add(Integer.valueOf(200));
        list.add(Integer.valueOf(100));
        list.add(Integer.valueOf(600));
        boolean remove = list.remove(Integer.valueOf (100) );  // removes first occurance and returns boolean
        //ArrayList<Integer> cloned = (ArrayList<Integer> list..clone())
        System.out.println(list + " " + remove);

        //String abc = list.remove(0);
        //System.out.println("b = " + b + "  abc is : " + abc);
        if(list.remove("TEO")) {
            list.remove("THREE");
        }
        System.out.println("size : " + list.size() + "  " + list.remove(0) + ":" + list.remove(null));

        LocalDate newYear = LocalDate.of(2018, 1, 1);
        LocalDate eventDate = LocalDate.of(2018, 1, 1);
        boolean flag1 = newYear.isAfter(eventDate);
        boolean flag2 = newYear.isBefore(eventDate);
        System.out.println(flag1 + ":" + flag2);

        final String fName = "James";
        String lName = "Gosling";
        String name1 = fName + lName;
        String name2 = fName + "Gosling";
        String name3 = "James" + "Gosling";
        System.out.println(name1 == name2);
        System.out.println(name2 == name3);

        //A obj = new Message(); //Line n1
        //obj.print(); //Line n2
        //change(obj); //Line n3
        //obj.print(); //Line n4

        List list1 = new ArrayList<String>(); //Line 5
        List<String> list2 = new ArrayList(); //Line 6
        //List<> list3 = new ArrayList<String>(); //Line 7
        List<String> list4 = new ArrayList<String>(); //Line 8
        List<String> list5 = new ArrayList<>(); //Line 9

        Boolean b1 = Boolean.valueOf("tRuE");
        Boolean b2 = Boolean.parseBoolean("fAlSe");
        Boolean b3 = Boolean.getBoolean("abc");
        Boolean b4 = Boolean.valueOf(b1);
        System.out.println(b1 + ":" + b2 + ":" + b3 + ":" + b4);

        int a = 2;
        boolean res = false;
        res = a++ == 2 || --a == 2 && --a == 2;
        System.out.println(a + "  " + res);

        List<String> dryFruits = new ArrayList<>();
        dryFruits.add("Walnut");
        dryFruits.add("Apricot");
        dryFruits.add("Almond");
        dryFruits.add("Date");

        ListIterator<String> iterator = dryFruits.listIterator();
        while(iterator.hasNext()) {
            if(iterator.next().startsWith("A")) {
                iterator.remove();
            }
        }

        System.out.println(dryFruits);

        int x = 5;
        while (x < 10)
            //System.out.println(x);   // infinet loop
            x++;

        Period period = Period.of(2, 1, 0).ofYears(10).ofMonths(5).ofDays(2);
        System.out.println(period);

        String [] arr = new String[7];
        System.out.println(arr);


        StringBuffer sbuff = new StringBuffer("LKO KJOJH  OISAOD ");
        sbuff.delete(0,5);
        System.out.println("sbuff = " + sbuff.length() + "  " + sbuff);

        LocalDate date = LocalDate.parse("2000-01-01");
        period = Period.ofYears(-3000);
        System.out.println(date.plus(period));

        int [] arr3 = new int[]{1,2,3};
        //add(10.0, new Integer(10));
        //add(10.0, null);

        int score = 60;
        switch (score) {
            default:
                System.out.println("Not a valid score");
            case 70:
                System.out.println("Failed");
                break;
            case 71:
                System.out.println("Passed");
                break;
        }
        System.out.println(new Child123());
        StringBuilder sb = new StringBuilder("Java");
        String s1 = sb.toString();
        String s2 = "Java";

        System.out.println(s1 == s2);
        //obj = new A(); //Line 7
        //System.out.println(obj.i1); //Line 8
        //System.out.println(obj.i2); //Line 9
        //System.out.println(obj.i3); //Line 10
        //System.out.println(obj.i4); //Line 11

        try {
           // m22();
        } finally {
            System.out.println("A");
        }

        m1(null);

        LocalDate d1 = LocalDate.parse("1999-09-09");
        LocalDate d2 = LocalDate.parse("1999-09-09");
        LocalDate d3 = LocalDate.of(1999, 9, 9);
        LocalDate d4 = LocalDate.of(1999, 9, 9);
        System.out.println((d1 == d2) + ":" + (d2 == d3) + ":" + (d3 == d4));

        Point p1 = new Point();
        p1.x = 17;
        p1.y = 35;
        p1.z = -1;

        Point p2 = new Point();
        p2.x = 19;
        p2.y = 40;
        p2.z = 0;

        System.out.println(p1); //Line n1
        System.out.println(p2); //Line n2

        List<StudentM> students = new ArrayList<>();
        students.add(new StudentM("James", 25));
        students.add(new StudentM("James", 27));
        students.add(new StudentM("James", 25));
        students.add(new StudentM("James", 25));

        students.remove(new StudentM("James", 25));

        for(StudentM stud : students) {
            System.out.println(stud);
        }
    }

    static void m1(CharSequence s) {
        System.out.println("CharSequence");
    }

    static void m1(String s) {
        System.out.println("String");
    }

    static void m1(Object s) {
        System.out.println("Object");
    }
    private static void m22() throws Exception {
        throw new Exception();
    }
    private static void add(double d1, double d2) {
        System.out.println("double version: " + (d1 + d2));
    }

    private static void add(Double d1, Double d2) {
        System.out.println("Double version: " + (d1 + d2));
    }
    public static void change(Message m) { //Line n5
        m.msg = "Happy Holidays!"; //Line n6
    }
}

class StudentM {
    private String name;
    private int age;

    StudentM(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "Student[" + name + ", " + age + "]";
    }

    public boolean equals(Object obj) {
        if(obj instanceof StudentM) {
            StudentM stud = (StudentM)obj;
            if(this.name.equals(stud.name) && this.age == stud.age) {
                return true;
            }
        }
        return false;
    }
}
class Point {
    static int x;
    int y, z;

    public String toString() {
        return "Point(" + x + ", " + y + ", " + z + ")";
    }
}
class Parent123 {
    public String toString() {
        return "Inner ";
    }
}

class Child123 extends Parent123 {
    public String toString() {
        return super.toString().concat("Peace!");
    }
}
class c3 implements I1 {
    public void m1() throws java.io.IOException{}
}
interface I1 {
    void m1() throws java.io.IOException;
}
abstract class Helper {
    int num = 100;
    String operation = null;

    protected abstract void help();

    void log() {
        System.out.println("Helper-log");
    }
}

class Message {
    String msg = "Happy New Year!";

    public void print() {
        System.out.println(msg);
    }
}
class Parent {
    int i = 10;
    Parent(int i) {
        super();
        this.i = i;
    }
}

class Child extends Parent {
    int j = 20;

    Child(int j) {
        super(0);
        this.j = j;
    }

    Child(int i, int j) {
        super(i);
        //this(j);
    }

}
class LogHelper extends Helper {
    private int num = 200;
    protected String operation = "LOGGING";

    protected void help() {
        System.out.println("LogHelper-help");
    }

    protected void log() {
        System.out.println("LogHelper-log");
    }

}

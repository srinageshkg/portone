package com.dcp.ifs.controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class JavaTest {
    public static void main(String[] args) {
        //ClassCastException, NumberFormatException and IllegalArgumentException are Runtime exceptions.
        //There are no exception classes in java with the names: NullException and ArrayIndexException.
        // switch can accept primitive types: byte, short, int, char; wrapper types: Byte, Short, Integer, Character; String and enums.

        // Postfix has got higher precedence than other operators
        // After postfix, precedence is given to prefix
        // == has higher precedence over && and ||
        // && has higher precedence over ||

        //res = a++ == 2 || --a == 2 && --a == 2;
        //((a++) == 2) || (((--a) == 2) && ((--a) == 2)); //[a=2, res=false].
        //    2 == 2 || (
        // true || (((--a) == 2) && ((--a) == 2)); [a=3, res=false].
        // || is a short-circuit operator, hence no need to evaluate expression on the right.


        Boolean ab = Boolean.parseBoolean("tRfue");
        Boolean cd = Boolean.valueOf("tRue");
        System.out.println("ab = " + ab  + " " + cd);
        Integer in = Integer.valueOf(10);
        Integer gi = Integer.getInteger("1000", 10);
        System.out.println("gi = " + gi);

        Sudhir sd = new Sudhir();
        System.out.println("sd.age = " + sd.name + "  " + sd.age);
        char c = 'Z';
        long l = 100_001;
        int ii = 99_9;
        float ff = 2.02f;
        double dd = 10_0.2D;

        l = (long)ff + ii;
        ff = c * l * ii * ff;

        System.out.println("l is = " + l);

        List<StringBuilder> ss = new ArrayList<>();
        StringBuilder ssun = new StringBuilder("Sunday");
        ss.add(ssun);
        ss.add(new StringBuilder("Monday"));
        ss.add(new StringBuilder("Tuesday"));

        if (ss.contains(new StringBuilder("Monday"))) {
            ss.add(new StringBuilder("FRIDAY"));
        }
        if (ss.contains(ssun)) {
            ss.add(new StringBuilder("Thursday"));
        }

        System.out.println("ss = " + ss);

        List<Integer> myints = new ArrayList<>();
        myints.add(0, Integer.valueOf(99));
        myints.remove(Integer.valueOf(0));
        myints.remove(0);

        List l1 = new ArrayList<String>();
        List l2 = new ArrayList();
        List<String> ls = new ArrayList();
        List<Integer> l3 = new ArrayList<>();

        l1.add("STRING");
        l1.add(3);

        ls.add("MY STRING");
        //ls.add(7);
        System.out.println("l1 = " + l1);

        //String [] arr = {"*", "**", "***", "****", "*****"};
        List<Sudhir> arr = new ArrayList<>();
        //arr.addAll(Arrays.asList("*", "**", "***", "****", "*****"));
        arr.add(new Sudhir("Nagesh", 58));
        arr.add(new Sudhir("Kavitha", 50));
        arr.add(new Sudhir("Ahkil", 25));

        Predicate<Sudhir> pr1 = s -> s.age > 30;  //
        print(arr, pr1);

        Period period = Period.of(2, 0, 2).ofYears(10).ofMonths(5).ofDays(2);
        Period np = Period.ofDays(1);
        System.out.println("np and period " + np + "  " + period);

        LocalDate date = LocalDate.of(2012, 1, 11);
        period = Period.ofMonths(2);
        // Easy way to remember is that Bigger(Upper case) letters represent something bigger.
        // M represents month & m represents minute, D represents day of the year & d represents day of the month.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-YY"); // "mm-DD-yy"
        System.out.println(formatter.format(date.minus(period)));

        // int can be converted to double but Integer type can't be converted to Double type
        // add(10.0, new Integer(10)); => 1st parameter is tagged to double primitive type and 2nd parameter is converted to int,
        // is tagged to double primitive type as well. So, add(double, double); method is invoked.
        // add(10.0, null); => Compiler can't convert null to double primitive type, so 2nd argument is tagged to Double reference type

        add(10.0, Integer.valueOf(10));
    }

    private static void print(List<Sudhir> arr, Predicate<Sudhir> predicate) {
        for(Sudhir str : arr) {
            if(predicate.test(str)) {
                System.out.println(str);
            }
        }
    }
    private static void add(double d1, double d2) {
        System.out.println("double version: " + (d1 + d2));
    }

    private static void add(Double d1, Double d2) {
        System.out.println("Double version: " + (d1 + d2));
    }

}

class Sudhir {
    String name;
    int age;

    Sudhir() {
        //Sudhir s = new Sudhir("James", 26);
        //System.out.println("s.name + \" \" + s.age = " + s.name + " " + s.age);
        this("James", 9_600);
    }

    public int getAge() {
        return age;
    }

    Sudhir(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " " + age;
    }
}

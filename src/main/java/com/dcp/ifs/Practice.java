package com.dcp.ifs;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;

public class Practice {

    private static String s;
    private static int [] arraa;
    static String msg;
    static Double d1;
    int x = d1.intValue();

    public static void main(String[] args) {

        String [] names = {"ABC", "DEV", "ERD"};
        List<String> listk = Arrays.asList(names);   // Returns a fixed length array can't add  and remove but can change
                                                // Change will applicable for both the Array and List
        listk.set(0,"SSSS");
        System.out.println("list[] = " + names[0]);

        List<String> naaa = new ArrayList<>();
        naaa.add("ZOOO");
        naaa.add(0, "TWHO");
        naaa.add(0, "DONKEY");

        System.out.println("naaa = " + naaa);

        Object[] naaaArray = naaa.toArray();
        System.out.println("naaaArray = " + Arrays.toString(naaaArray));
        naaaArray[1] = "FIVE";
        System.out.println("naaaArray = " + Arrays.toString(naaaArray));
        System.out.println("naaa = " + naaa);     // These two are different

        // sort
        Collections.sort(naaa);
        System.out.println("Sorted naaa = " + naaa);

        for (int i=0; i<naaa.size(); i++) {
            System.out.println("i  = " + i + " " + naaa.get(i) + "   size :" + naaa.size());
            System.out.println("within 1 for naaa = " + naaa);
            //naaa.remove(2);   // indexOutOfBoundsException
            System.out.println("within 2 for naaa = " + naaa);
        }
        for(String s : naaa) {
            System.out.println("s = " + s);
            //naaa.remove(2);   // Concurrent modification error
        }
        System.out.println("...size and after for remove naaa = " + naaa.size() + "  " + naaa);
        naaa.add("UHDT");
        naaa.add("JTGIS");
        Collections.sort(naaa);
        System.out.println("added elements Sorted naaa = " + naaa);
        for (Iterator<String> iterator = naaa.iterator(); iterator.hasNext() != false; ) {
            // hasNext, next, remove
            String str = iterator.next();
            //iterator.remove();
        }
        System.out.println("naaa = " + naaa);

        for (ListIterator<String> listIterator = naaa.listIterator(naaa.size()-1); listIterator.hasPrevious();) {
            System.out.println("listIterator.previous() = " + listIterator.previous());
        }
        //LocalDate obj = LocalDate.now();
        //System.out.println(obj.getHour());
 /*       try {
            System.out.println(s.length());
        } catch(NullPointerException | RuntimeException ex) {
            System.out.println("DONE");
        }*/
        System.out.println("HELLO");

        int[] arr = {1,2,3,4,5,6,7,8,9,10};

        int[] ra = rotatearr(arr, arr.length-7);
        for (int i=0; i< ra.length; i++) {
            System.out.printf("%d, ".formatted(ra[i]));
        }

        int k;
        for (k=0; k<=2; k++) {}
        System.out.println("k...... = " + k);

        String [] arrr = {"*", "**", "***", "****", "*****", "******"};
        Predicate<String> pr1 = s -> s.length() < 4;
        print(arrr, pr1);

        int a = 3;
        System.out.println(a++ == 3 || --a == 3 && --a == 3);
        //var bool = new Boolean(true);
        LocalDate date22 = LocalDate.parse("2018-01-01");
        System.out.println(date22);
        double ad = 10;
        byte aaaaa = 127;

        StringBuilder sbsss = new StringBuilder();





        Word obj = new Word(25, "Text");
        System.out.println(obj.type + ", " + obj.pages);

        Animal cat = new Cat();
        Animal deer = new Deer();
        cat.jump();
        deer.jump();

        StringBuilder sb = new StringBuilder("Hello");
        List<StringBuilder> list = new ArrayList<>();
        list.add(sb);
        list.add(new StringBuilder("Hello"));
        list.add(sb);
        sb.append("World!");

        System.out.println(list);

        System.out.println( 23 / 2.0 );
        System.out.println( 23 % 2.0 );

        TestEx objj = null;
        objj.print();

        //objj.add(10.0, new Double(10.0));

        int i;
        outer:
        do {
            i = 5;
            inner:
            while (true) {
                System.out.println(i--);
                if (i == 4) {
                    break outer;
                }
            }
        } while (true);

        StringBuilder sb1 = new StringBuilder();
        try {
            //for(;;) {
            //    sb1.append("OCA");
            //}
        } catch(Exception e) {
            System.out.println("Exception!!!");
        }
        System.out.println("Main ends!!!");

        LocalDate date = LocalDate.of(2068, 4, 15);
        System.out.println(date.getMonth() + ":" + date.getMonthValue());

        A obj1 = new C();
        A obj2 = new B();
        C obj3 = (C)obj1;
        //C obj4 = (C)obj2;
        obj3.print();

        int var = '7';
        switch(var) {
            case '7':
                System.out.println("Lucky no. 7");
                break;
            default:
                System.out.println("DEFAULT");
        }
        LocalTime time = LocalTime.of(23, 59);
        System.out.println(time);
        String fName = "James";
        String lName = "Gosling";
        System.out.println(fName = lName);

        Boolean [] arr5 = new Boolean[2];
        List<Boolean> list5 = new ArrayList<>();
        list5.add(arr5[0]);
        list5.add(arr5[1]);
        System.out.println(list5.get(0) + " " + list5.get(1) );
        if(list5.remove(0) == null) {
            list5.remove(0);
        }

        System.out.println(list5);
        System.out.println(LocalDateTime.now());

        int aa = 7;
        boolean res = aa++ == 7 && ++aa == 9 || aa++ == 9;
        System.out.println("aa = " + aa);
        System.out.println("res = " + res);

        String [] arr9 = {"A", "B", "C", "D"};
        arr9[0] = arr9[1];
        arr9[1] = "E";
        for(String s : arr9) {
            System.out.print(s + " ");
        }
        System.out.println();
        StringBuilder sbsb = new StringBuilder();
        System.out.println(sbsb.append("").append("").append("").length());

        //if(arraa.length > 0 && arraa != null) {
        //    System.out.println(arraa[0]);
        //}

        char [][] arrrr = {
                {'A', 'B', 'C'},
                {'D', 'E', 'F'},
                {'G', 'H', 'I'}
        };

        for(int n = 0; n < arrrr.length; n++) {
            for(int j = 0; j < arrrr[n].length; j++) {
                System.out.print(arrrr[n][1]);
            }
            System.out.println();
        }

        List<Character> listc = new ArrayList<>();
        listc.add(0, 'V');
        listc.add('T');
        listc.add(1, 'E');
        listc.add(3, 'O');

        if(listc.contains('O')) {
            listc.remove(3);
        }

        for(char ch : listc) {
            System.out.print(ch);
        }

        String msg; //Line 4
        if(args.length > 0) {
            msg = args[0]; //Line 6
        }
        //System.out.println(msg); //Line 8
        System.out.println();

        String [] arr12 = {"abc", "TrUe", "false", null, "FALSE"};
        for(String s : arr12) {
            System.out.print(Boolean.valueOf(s) ? "T" : "F");
        }
        System.out.println();
        List<String> dryFruits = new ArrayList<>();
        dryFruits.add("Walnut");
        dryFruits.add("Apricot");
        dryFruits.add("Almond");
        dryFruits.add("Date");

        for(String dryFruit : dryFruits) {
            if(dryFruit.startsWith("A")) {
                //dryFruits.remove(dryFruit);
            }
        }

        System.out.println(dryFruits);
        extractInt(2.7);
        extractInt(2d);

        byte b1 = (byte) (127 + 21);
        System.out.println(b1);

        int aaa = 5;
        int x = 10;
        switch(x) {
            case 10:
                aaa *= 2;
            case 20:
                aaa *= 3;
            case 30:
                aaa *= 4;
        }
        System.out.println(aaa);

        Period period = Period.of(0, 1000, 0);
        System.out.println(period);

        LocalDate date1 = LocalDate.parse("1947-08-15", DateTimeFormatter.ISO_DATE);
        LocalDate date2 = LocalDate.parse("1947-08-15",
                DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate date3 = LocalDate.of(1947, 8, 15);

        System.out.println(date1.equals(date2) + " : " + date2.equals(date3));
    }


    private static void extractInt(Double obj) {
        System.out.println(obj.intValue());
    }
    static void print(String [] arr, Predicate<String> predicate) {
        for(String str : arr) {
            if(predicate.test(str)) {
                System.out.println(str);
            }
        }
    }
    public static int[] rotatearr(int[] arr ,int times) {
        for (int i=0; i< arr.length; i++) {
            System.out.printf("%d, ".formatted(arr[i]));
        }
        System.out.println();
        int[] newar = Arrays.copyOf(arr, arr.length);
        System.out.println("Copied Array: ");
        for (int i=0; i< newar.length; i++) {
            System.out.printf("%d, ".formatted(newar[i]));
        }
        System.out.println();
/*        for (int i=0; i<times; i++) {
            for (int j=newar.length -1; j>0 ; j--) {
                int temp = newar[j];
                newar[j] = newar[j-1];
                newar[j-1] = temp;
            }
        }*/
        for (int i=0; i<times; i++) {
            int temp = newar[newar.length -1];
            for (int j=newar.length -1; j>0 ; j--) {
                newar[j] = newar[j-1];
            }
            newar[0] = temp;
        }
        System.out.println();
        return newar;
    }
}

class Doc {
    int pages;
    Doc (int pages) {
        this.pages = pages;
    }
}

class Word extends Doc {
    String type;
    Word(String type) {
        super(20);
        this.type = type;
    }

    Word(int pages, String type) {
        this(type);
        //super(pages);
    }

}

class Animal {
    protected void jump() {
        System.out.println("Animal");
    }
}

class Cat extends Animal {
    public void jump(int a) {
        System.out.println("Cat");
    }
}

class Deer extends Animal {
    public void jump() {
        System.out.println("Deer");
    }
}

class A {
    public void print() {
        System.out.println("A");
    }
}

class B extends A {
    public void print() {
        System.out.println("B");
    }
}

class C extends A {
    public void print() {
        System.out.println("C");
    }
}

class TestEx {

    public static void print() {
        System.out.println("static method");
        double area = 5.7;
        String color=null;
        if (area < 7) {
            color = "green";
        }
        System.out.println(color);
    }
    private static void add(double d1, double d2) {
        System.out.println("double version: " + (d1 + d2));
    }

    private static void add(Double d1, Double d2) {
        System.out.println("Double version: " + (d1 + d2));
    }
}

abstract class Animal1 {}
class Dog extends Animal1{}

class Class1 {
    public void read() throws FileNotFoundException {}
}

class Class2 {
    String Class2;
    public void Class2() {}
}

class Class3 {
    private void print() {
        //private String msg = "HELLO";
        System.out.println("msg");
    }
}

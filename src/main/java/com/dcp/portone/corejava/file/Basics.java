package com.dcp.portone.corejava.file;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Basics {
    public static void main(String[] args) {

        try {
            FileReader reader = new FileReader("info.properties");
            Properties obj_p = new Properties();
            obj_p.load(reader);
            System.out.println(obj_p.getProperty("user"));
            System.out.println(obj_p.getProperty("password"));
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        char alpha = 'c';

        String val = switch (alpha) {
            case 'c', 'C' -> {yield "Charlie";}
            case 'd' -> "Its between 2 and 4 - ";
            default -> {String returnStr = "Its default and is bad " + alpha;
                    yield returnStr;}
        };
        System.out.println("String" + val);

        try {
            //System.out.println("Age  = " + getInputFromConsole(2023));
        } catch (NullPointerException e) {
            //System.out.println("Age  = " + getInputFromScanner(2023));
        }

        // Very Basics
        Y y = new Y();
        System.out.println("Static and normal blocks: " + y.i);    //L5

        TestI ti = new TestI();
        System.out.println("TestI counter = " + ti);

        //fun(12);
        HashSet hashSet = new HashSet();
        hashSet.add(10);
        hashSet.add("abcd");
        hashSet.add(789);
        hashSet.add("HashSet");

        Iterator iterator = hashSet.iterator();

        while (iterator.hasNext()) {
            //System.out.println("iterator = " + iterator.next());
            if (iterator.next().equals("abcd"))
                ;//iterator.remove();
        }
        System.out.println("-----------------------------------");
        hashSet.forEach(s -> System.out.println(s));
        System.out.println("iterator is " + (hashSet.isEmpty() ? "" : " NOT ") + " empty " + hashSet.toString());

        //Collections.shuffle();
        System.out.println("-----------------------------------");

        hashSet.forEach(s -> System.out.println(s));

        Map hashMap = new HashMap();
        hashMap.put(1, 10);
        hashMap.put(2, "abcd");
        //hashMap.put(3,null);

        if (hashMap.containsKey(1))
            hashMap.replace(1, 99);
        for (var key: hashMap.keySet()) {
            System.out.println("key = " + hashMap.get(key));
        }
        hashMap.forEach((k, v) -> System.out.println("HashMap[" + k + " :" + v + " ]"));

        Iterator ithm = hashMap.entrySet().iterator();
        while (ithm.hasNext()) {
            Map.Entry<Integer, Object> pair = (Map.Entry<Integer, Object>) ithm.next();
            System.out.println("HashMap[" + pair.getKey() + " :" + pair.getValue() + " ]");
        }

        System.out.println("iterator is " + (hashSet.isEmpty() ? "" : " NOT ") + " empty " + hashSet.toString());

        Hashtable hashtable = new Hashtable<>(hashMap);
        hashtable.put(3,"null");
        hashtable.put(4,"EnumeratedList");
        //hashtable.get(2);
        hashtable.forEach((k, v) -> System.out.println("HashTable[" + k + " :" + v + " ]"));

        Enumeration enumerationHashTable = hashtable.elements();
        while (enumerationHashTable.hasMoreElements()) {
            System.out.println("Enumerated HashTable list: " + enumerationHashTable.nextElement());
        }

        //hashtable.clone();
        List<String> fillList = new ArrayList<>(10);
        //fillList.set();

    }

    public static int getInputFromConsole(int currentYear) {
        String name = System.console().readLine("Name Please");
        System.out.println("Your name = " + name);
        String year = System.console().readLine("Year you were born?");
        int yearBorn = Integer.parseInt(year);

        System.out.println("Age is = " + (currentYear - yearBorn));
        return (currentYear - yearBorn);
    }

    public static int getInputFromScanner(int currentYear) {

        Scanner scanner = new Scanner(System.in);
        //Scanner sc = new Scanner(new File("article.txt"));
        System.out.println("What's your name? ");
        String name = scanner.nextLine();
        System.out.println("Your name is: " + name);
        System.out.println("Year you were born?");
        int yearBorn = scanner.nextInt();

        System.out.println("Age is = " + (currentYear - yearBorn));
        return (currentYear - yearBorn);

        //method1(40,20);
    }
    public void method1 (int num1,float num2){
        System.out.println("int-float method");
    }
    public void method1(float num1,int num2){
        System.out.println("float-int method");
    }
    static int fun (int n)
    {
        int result;
        result = fun (n - 1);
        return result;
    }

}

class NumberPalindrome {
    public boolean isPalindrome(int num) {
        int tempnum = num;
        if (num < 0) {
            tempnum = Math.abs(num);
        }
        int reverse = 0;
        int tmp = 0;

        while (tempnum > 9) {
            tmp = tempnum % 10;
            tempnum = tempnum / 10;
            reverse = reverse + (10 * tmp);
        }

        reverse = reverse + (10 * tmp);
        if (reverse == num) {
            return true;
        }
        return false;
    }
}

class X {
    static int i = 1111;

    static{
        System.out.println("X S i = " + i);
        i = i-- - --i;    //L1
        System.out.println("X S i = " + i);
    }

    {
        System.out.println("X i = " + i);
        i = i++ + ++i;    //L2
        System.out.println("X i = " + i);
    }
}
class Y extends X{
    static{
        System.out.println("Y S i = " + i);
        i = --i - i--;    //L3
        System.out.println("Y Si = " + i);
    }
    {
        System.out.println("Y i = " + i);
        i = ++i + i++;    //L4
        System.out.println("Y i = " + i);
    }
}

class TestI {
    private static int counter = 0;
    public void TestI() {
        counter = 20;
    }
    TestI() {}
    TestI(int x) {
        counter = x;
    }
}

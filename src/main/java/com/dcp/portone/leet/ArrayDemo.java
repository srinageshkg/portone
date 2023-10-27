package com.dcp.portone.leet;

import com.dcp.portone.model.response.Employee;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

public class ArrayDemo {

    public static void main(String[] args) {

        ArrayDemo ad = new ArrayDemo();
        ad.strDemo();
    }

    public int[] returnSomething() {
        return new int[] {1, 12, 4, 72, 7, 9, 10};
    }

    public void demo(){
        this.intDemo();
        this.strDemo();
    }
    public void intDemo() {

        ArrayList<Integer> ma = new ArrayList<>(10);
        for (int i=0; i<7; i++ ) {
            ma.add(i + 101);
        }
        ma.add(28); ma.add(28); ma.add(28);ma.add(29);
        System.out.println("ma.size() = " + ma.size());
        ma.forEach(System.out::println);
        int[] intArr = new int[]{4, 7, 10, 12, 35};
        int[] intArr1 = {4, 7, 10, 12, 35};
        int arr1[] = {4, 7, 10, 12, 35};
        int[] arr;
        arr = new int[5];
        arr[0] = 12;

        List<Integer> intOArr = new ArrayList<>(Arrays.asList(5, 8, 23, 51, 21, 7, -10, 25));

        IntSummaryStatistics isstats = intOArr.stream().mapToInt(n -> n).summaryStatistics();
        System.out.println("Stats: Avg:" + isstats.getAverage() + " Max: " + isstats.getMax() + "Tot: " + isstats.getSum() );

        for (var i : intArr ) {
            //System.out.println(i);
        }
        arr1 = returnSomething();

        int[] arr2 = arr1.clone();
        Arrays.sort(arr2);
        int key = 12;

        System.out.println("Found the key at index : " + Arrays.binarySearch(arr2, key));
        System.out.println("Copy of with 10 ele : " + Arrays.toString(Arrays.copyOfRange(arr2, 4, 10)));

        try {
            for (int i=0; i< arr2.length; i++) {
                System.out.println(arr2[i]);
            }
        }catch (ArrayIndexOutOfBoundsException ai){
            System.out.println(ai.getMessage());
        }finally {
            System.out.println("Finally - Reduce the arr length");
        }
        //strDemo();
        this.ispolin();
    }

    public void ispolin(){
        int p = 1567651;
        boolean isp = false;
        int revp = IntStream.iterate(p, i-> i/10)
                .map(n->n%10)
                .limit(String.valueOf(p).length())
                .reduce(0,(a,b) -> a * 10 + b);

        System.out.println("Is poli " + p + " and " + revp + " is: " + (p == revp));

        Random random = new Random();
        Integer in = 18765;
        int k = String.valueOf(in).length();
        System.out.println("String Limit: " + in + " " + k + " i by 10 :" + in/10 + " i %10: " + in %10 );
        random.ints().limit(5).forEach(System.out::println);

        // Spliterator
    }

    public void strDemo(){
        String[][] subs = {{"Csub1","Csub2","CSub3"},{"Msub1","MSub2","MSub3"},{"ESub1","ESub2","Esub3"}};

        Set set = new HashSet();
        String[][] subcp = new String[3][3];

        for( String[] sa: subs){
            for(String s: sa){
                System.out.println(s);
            }
        }
        Random random = new Random();
        Employee[] empArr = (Employee[]) Array.newInstance(Employee.class, 5);
        ArrayList<Employee> empal = new ArrayList<>();
        for (int i=0; i<empArr.length; i++){
            empArr[i] = new Employee();
            empArr[i].setEmpId((long) random.nextLong(1, 100));
            empArr[i].setEmpName("EmpName"+ (i+1));
            empal.add(new Employee());
        }
        int i = 0;
        for (var e: empal){
            e.setEmpId((long) (long) random.nextLong(101, 200));
            e.setEmpName("ALName"+e.getEmpId() + ++i);
        }
        for (Employee e: empArr){
            System.out.println("empArr: " +  e.toString());
        }
        for (Employee e: empal){
            System.out.println("empal: " + e.toString());
        }
    }
}

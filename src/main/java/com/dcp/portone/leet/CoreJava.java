package com.dcp.portone.leet;

import com.dcp.portone.service.Studentiit;
import com.dcp.portone.service.ThreePrizes;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CoreJava { //implements Predicate {

    // Lamda expressions
    public int transformAndAdd(List<Integer> li, Function<Integer, Integer> ops) {
        int result = 0;
        for (Integer i: li){
            result += ops.apply(i);
        }
        System.out.println("tra and add: " + result);
        return result;
    }
    public void funcinterface () {

        Function<Integer, Integer> getcube = (i) -> i * i * i;
        Predicate<Integer> predicate = value -> value != null;       // returns boolean
        Supplier supplier = () -> 5;
        Predicate<String> sp = str -> str.startsWith("N");
        Consumer consumer = value -> System.out.print("Consu :" + value + " -"); // returns nothing

        Square sq = (int a) -> a * a;
        //Operation area = (a, b) -> a * b;
        //Operation addition = (a, b) -> a + b;

        Function<Integer, Double> half = a  -> (a / 2.0);

        List<String> slist = Arrays.asList("My", "Name", "", "Nagesh.");
        String[] myArr = new String[] {"stream",   "is",  "a", "sequence", "of",  "elements", "like",  "list"};
        String[] myarray = "I am 24 years old and I want to be in Tier I company".split(" ");
        ArrayList<Integer> ilist = new ArrayList<>(Arrays.asList(4, 16, 9, 34, 9, 15, 18));

        //ilist.forEach(n -> consumer.accept(n));
        Stream.of(myArr).filter(s -> s.matches("(a|e|i|o|u)\\w*")).forEach(System.out::println);
        Collections.sort(ilist);

        ilist.forEach(n -> consumer.accept(n));

        // OpsUtil
        consumer.accept(transformAndAdd(ilist,i -> OpsUtil.addOp(i)));
        consumer.accept(transformAndAdd(ilist, OpsUtil::triple));

        try {
            half = half.compose(a -> 3 + a);
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
        // 10 * 10 then 3 + 100 then 103/2.0 then Consumes to print
        //System.out.println(half.apply(s.calculate(15)));
        consumer.accept(half.apply(sq.operate(10)));
        //consumer.accept(half.apply(area.operate(10, 15)));
       // consumer.accept(half.apply(addition.operate(10, 15)));
        consumer.accept(half.apply(getcube.apply(10)));
        // Functional Interface Java 8 - lambda expressions can be used to represent the instance of a functional interface
        new Thread(() -> {
            consumer.accept("New Thread Created... ");
        }).start();

        for (String st: slist){
            if (sp.test(st)) {
                consumer.accept(st);
            }
        }

        List<Studentiit> stuDetails = new ThreePrizes().getStudentiitList();
        System.out.println();
        String nameslist = stuDetails.stream().map(s -> s.getName()).collect(Collectors.joining(", "));
        System.out.println(nameslist);
        Integer sum = stuDetails.stream().map(s -> s.getMarksiit().getMaths()).reduce(0,Integer::sum);
        List<String> names = new ArrayList<>();
        System.out.println(sum);
        names = stuDetails.stream().map(studentiit -> studentiit.getName()).sorted().collect(Collectors.toList());

        HashMap<String, Integer> namesmap = names.stream()
                .collect(Collectors.toMap(
                        Function.identity(), String::length,
                        (e1, e2) -> e1, HashMap::new
                ));
        System.out.println("Names in Hash Map are " + namesmap);

        Collections.sort(names);
        System.out.println(names);

        Stock st1 = Stock.builder()
                .name("Google")
                .price(120.88)
                .build();

        Stock st2 = Stock.builder()
                .name("Apple")
                .price(157.64)
                .build();

        Stock st3 = Stock.builder()
                .name("Microsoft")
                .price(320.25)
                .build();

        List<Stock> stocks = new ArrayList<>(Arrays.asList(st1, st2, st3));

        List<String> stocknames = stocks.stream()
                .filter(st -> st.getPrice() > 150.8)
                .map(st -> st.getName())
                .collect(Collectors.toList());
        System.out.println("Stock Names " + stocknames);

        Double maxValue = stocks.stream()
                .map(st -> st.getPrice())
                .reduce(0.00, Double::max);
        System.out.println("Stock Value " + maxValue);

        List<String> stocknames1 = stocks.stream().map(s -> s.getName()).collect(Collectors.toList());
        Comparator<String> comp = (String s1, String s2) -> s1.compareTo(s2);
        Collections.sort(stocknames1, comp);
        System.out.println(" Sorded stock names: " + stocknames1);
    }
}

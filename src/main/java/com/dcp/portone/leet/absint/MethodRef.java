package com.dcp.portone.leet.absint;


import java.util.*;
import java.util.function.*;

class PlainOld {
    private static int last_id = 1;
    private int id;
    public PlainOld() {
        id = last_id++;
        System.out.println("Creating a plain old Object" + id);
    }
}

public class MethodRef {
    static public String methodRef = """
            1. reference a static method
            2. instance method a) external to the epression or 2) passed as on arg
            3. a constructor by using a new::
            4. Type ref referce to a class, interface, enum, record names - NOT True for Method references
            5. Static methods must be invoked using a ref type only in method reference and lambda expressions
            2 ways - 1. when you refer to the method with an instance derived from the enclosing code 
                (declared out side the method ex System.out::print - bounded receiver
                    2. the instance used to invoke the method. (first arg passed to lambda or method ref when executed - unbounded receiver
            ex; String::concat is not a static method but it is still valid
            () - new Student() ---- (p1, p2) -> p1.concat(p1) ---- 
            6. Supplier , Predicate, Consumer, Function, unary
            
            Comparator Interface - static method on Comparator is "comparing" - takes function as a method parameter and then returns comparator
            
            Collecton - A unified Architecture for representing and implementing collections, enabling coleections to be manipulated 
            independently of implementation details
            
            https://docs.oracle.com/javase/8/docs/technologies/guides/collections/overview.html
            
            https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html
            
            
            """;

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("Anna", "Bob", "Chuck", "Dave"));

        //list.forEach(System.out::printf);

        calculator((a,b)-> a % b, 13, 5 );
        calculator((a,b)-> a * b, 13, 5 );
        calculator(Integer::compare, 10, 13 );
        calculator(Double::sum, 22.5, 13.3 );
        calculator((s1, s2) -> s1 + s2, "Hellow", " World");
        calculator((s1, s2) -> s1.concat(s2), "Hellow", " World");
        calculator(String::concat, "Hellow", " World");

//        PlainOld pojo1 = new PlainOld();
//        Supplier<PlainOld> pojosupfunint = () -> new PlainOld();
        Supplier<PlainOld> pojosupfunintref = PlainOld::new;

        PlainOld newPojo1 = pojosupfunintref.get();
        System.out.println("Getting Array ---");
        PlainOld[] pojo1 = seedArray(PlainOld::new, 3);

        BinaryOperator<String> b1 = String::concat;
        BiFunction<String, String, String> b2 = String::concat;
        UnaryOperator<String> u1 = String::trim;

        System.out.println("b1 = " + b1.apply(" Hel l o", " Wor ld "));
        System.out.println("b2 = " + b2.apply(" Hel l o", " Wor ld "));
        System.out.println("u1 = " + u1.apply("  Hel l o  "));

        String result = "Bread".transform(String::toUpperCase);
        System.out.println("Bread result = " + result);
        String res = "";

        Function<String, Boolean> fb = String::isEmpty;
        boolean booleanResult = res.transform(fb);
        System.out.println("booleanResult = " + booleanResult);

        List<UnaryOperator<String>> list2 = new ArrayList<>(List.of(
                String::toUpperCase//,
                //g - > g += " " + getRandomChar('B', 'U') + "."
        ));
        String[] cars = {"Honda", "Toyota", "FOrd", "Lexus", "bmw"};
        applyChanges(cars, list2);

        Function<String, String> uCase = String::toUpperCase;
        Function<String, String> lastName = s -> s.concat(" Ganji");
        Function<String, String> uCaseLastName = uCase.andThen(lastName);
        List<String> newcars = new ArrayList<>();

        for (String car: cars) {
            //uCase.apply(car);
            newcars.add(uCaseLastName.apply(car));
            //System.out.println("car = " + uCaseLastName.apply(car));
        }

        System.out.println("newcars = " + newcars);

        uCaseLastName = uCase.compose(lastName);
        System.out.println("uCaseLastName.apply(\"KJYHKJ\") = " + uCaseLastName.apply("KJYHKJ"));

        Function<String, String[]> f0 = uCase
                .andThen(s -> s.concat(" Ganji"))
                .andThen(s -> s.split(" "));

        Function<String, String> f1 = uCase
                .andThen(s -> s.concat(" Ganji"))
                .andThen(s -> s.split(" "))
                .andThen(s -> s[1].toUpperCase() + " " + s[0]);

        Function<String, Integer> f2 = uCase
                .andThen(s -> s.concat(" Ganji"))
                .andThen(s -> s.split(" "))
                .andThen(s -> String.join(", ", s))
                .andThen(String::length);

        Consumer<String> c0 = s-> System.out.print(s.charAt(0));
        Consumer<String> c1 = s-> System.out.print(s);
        newcars.forEach(c0
                .andThen(s -> System.out.print(" - "))
                .andThen(c1)
                .andThen(s -> System.out.println()));

        Predicate<String> p1 = s -> s.equals("Tim");
        Predicate<String> p2 = s -> s.equalsIgnoreCase("tim");
        Predicate<String> p3 = s -> s.startsWith("Tim");
        Predicate<String> p4 = s -> s.endsWith("Tim");

        String nn = "Jumping";
        Predicate<String> combined1 = p1.or(p2);
        System.out.println("combined1.or = " + combined1.test(nn));
        Predicate<String> combined3 = p1.and(p2).negate();
        System.out.println("combined3.or.negate = " + combined3.test(nn));

        record Person(String first, String last) {}

        Person pp1 = new Person("Andy", "Thomas");
        Person pp3 = new Person("Bob", "Mathews");
        //List<Person> persons = Arrays.asList(pp1,pp3);


        List<Person> listP = new ArrayList<>(Arrays.asList(
                new Person("Akhil", "Ganji"),
                new Person("Nagesh", "Perumal"),
                new Person("Navesh", "Sambuth"),
                new Person("Super", "Devil") ));

        System.out.println("listP = " + listP);
        listP.sort(Comparator.comparing(Person::last)
                .thenComparing(Person::first)
                .reversed());
        listP.forEach(System.out::println);

        //Collections.addAll(listP, persons);


    }
    /*
    private static <T> void calculator(BinaryOperator<T> function, T v1, T v2) {
        T result = function.apply(v1,v2);
        System.out.println("result of operation: " + result);
    }
    */
    private static <T> void calculator (BinaryOperator<T> func, T val1, T val2) {
        T result = func.apply(val1, val2);
        System.out.println("result of the op: " + func.getClass().getSimpleName() + " -- " + result);
    }

    private static PlainOld[] seedArray(Supplier<PlainOld> reference, int cnt) {
        PlainOld[] array = new PlainOld[cnt];
        Arrays.setAll(array, i -> reference.get());
        return array;
    }

    private static void applyChanges(String[] names, List<UnaryOperator<String>> stringFunctions ) {
        List<String> backedByArray = Arrays.asList(names);
        for (var function: stringFunctions) {
            backedByArray.replaceAll(s -> s.transform(function));
            System.out.println(Arrays.toString(names));
        }
    }
    public static char getRandomChar(char startChar, char endChar) {
        return (char) new Random().nextInt((int)startChar, (int)endChar + 1);
    }
}

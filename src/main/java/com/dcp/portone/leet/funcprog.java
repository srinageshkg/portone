package com.dcp.portone.leet;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

class Main {
    record Person(String firstName, String lastName) {
        @Override
        public String toString() {
            return firstName + " < > " + lastName;
        }
    }

    class PlainOld {
        private static int lastId = 1;
        private int id;

        PlainOld(){
            id = lastId++;
            System.out.printf("PlainOld %3d",id);
        }
    }

    public static void main(String[] args) {
        //lamdaexSecondLevel();
        //lamvariety();
        //testFuncIntr();
        spiralOrder();
        //deckDemo();
        //cardControler();
        //contactData();
        //LinkedLists ll = new LinkedLists();
        //ll.main();
        //ll.mainPlaces();
    }
    // Functional programming promotes writing code that is mode declarative. Treats computation as the
    // evaluation of mathematical functions and avoids changing-state and mutable data.
    // lamda expressions, Functional interface, Stream API, Default methods in interface, immutability final and var
    // Consumer - BiConsumer void accept(T t)
    // Function R apply(T t)
    // Predicate boolean test(T t)
    // Supplier void T get()
    // Arrays.asList()  List.of()

    public static void contactData() {
        List<Contact> emails = ContactData.getData("email");
        printContactData("Contacts Email Data", emails);
        List<Contact> phones = ContactData.getData("phone");
        printContactData("Contacts Phone Data", phones);

        ContactData.readScannerData(5);

        Set<Contact> emailContacts = new HashSet<>(emails);
        Set<Contact> phoneContacts = new HashSet<>(phones);

        int index = emails.indexOf(new Contact("Nagesh Ganji"));
        Contact ng = emails.get(index);
        ng.addEmail("Disney Land");

        printContactData("Contacts email Data", emailContacts);
        printContactData("Contacts Phone Data", phoneContacts);

        ng.replaceEmailIfExists("srina@disney.com", "sganji@gmail.com");
        printContactData("Contacts email Data", emailContacts);

        Set<Contact> contactAUB = new HashSet<>();
        contactAUB.addAll(emailContacts);
        contactAUB.addAll(phoneContacts);
        printContactData("A \u222A B Union of Emails with Phones.", contactAUB);

        Set<Contact> contactAIntB = new HashSet<>(emailContacts);
        contactAIntB.retainAll(phoneContacts);
        printContactData("A \u2229 B Inter of Emails with Phones.", contactAIntB);

        List<Set<Contact>> sets = List.of(emailContacts,phoneContacts, contactAUB);
        int minval = Integer.MIN_VALUE;
        int maxval = Integer.MAX_VALUE;
        System.out.println("maxval = " + maxval + "Min vaal = " + minval);

        for (var consSet : sets){
            contactAIntB.addAll(consSet);
        }

    }

    public static void printContactData(String header, Collection<Contact> contacts){
        System.out.printf("--------------------------");
        System.out.println(header);
        contacts.forEach(System.out::println);
    }


    public static void testFuncIntr (){
        int resulta = calulator((a,b)-> a + b, 5, 3);
        int resultb = calulator(Integer::sum, 5, 3);

        var resultm = calulator((a,b)-> a / b, 5.4, 3.2);
        var results = calulator((a, b) -> a.toUpperCase() + " " + b.toUpperCase(),"Ralph", "Venkat");

        var coords = Arrays.asList(
                new double[]{23.6577, 87.4464},new double[]{64.1565, 54.6464},new double[]{65.6664, 58.6446});
        coords.forEach(s-> System.out.println(Arrays.toString(s)));

        BiConsumer<Double, Double> p1 = (lat, lon) -> System.out.printf("[lat:%.3f lon:%.3f]%n", lat, lon);
        var firstPoint = coords.get(0);
        processPoint(firstPoint[0], firstPoint[1], p1);

        coords.forEach(s -> processPoint(s[0], s[1], p1));
        coords.forEach(s -> processPoint(s[0], s[1], (lat, lon) -> System.out.printf("[lat:%.3f lon:%.3f]%n", lat, lon)));

        //Supplier<PlainOld> reference1 = () -> PlainOld::new;
    }
    public static <T> void processPoint(T t1, T t2, BiConsumer<T, T> consumer){
        consumer.accept(t1, t2);
    }
    public static <T> T calulator(BinaryOperator<T> function, T val1, T val2) {
        T result = function.apply(val1, val2);
        System.out.println("result of operation = " + result);
        return result;
    }

    public static <T> T myCalculator(Operation<T> myFunc, T v1, T v2) {
        T result = myFunc.operate(v1, v2);
        return result;
    }

    public static void spiralOrder() {
        var coords = Arrays.asList(
                new double[]{1.234, 4.456},
                new double[]{2.234, 5.456},
                new double[]{3.234, 6.456}
        );

        coords.forEach(System.out::println);

        BiConsumer<Double,Double> p1 = (lat, lag) -> System.out.printf("[lat%.2f, lag%.2f]",lat, lag);
        String[] str = {"Tom", "Bob", "Mathew", "Rob"};
        List<String> strs = new ArrayList<>(List.of("Tom", "Bob", "Mathew", "Rob"));

        Arrays.stream(str).forEach(s->s.equalsIgnoreCase("Bob"));
        strs.removeIf(s->s.equalsIgnoreCase("Bob"));
        strs.addAll(List.of("alpha", "bravo", "charlie", "delta"));
        strs.removeIf(s->s.startsWith("ea"));
        strs.replaceAll(s->s.charAt(0) + " - " + s.toUpperCase());
        strs.replaceAll(s->s.charAt(0) + s.substring(0,1).toUpperCase());

        String[] emptyEle = new String[10];
        System.out.println(Arrays.toString(emptyEle));

        Arrays.fill(emptyEle,"");
        Arrays.setAll(emptyEle, (i)-> "" + (i+1) + ". ");

        Arrays.setAll(emptyEle, i-> "" + (i+1) + ". " +
                switch (i) {
                    case 0 -> "one";
                    case 1 -> "two";
                    case 2 -> "three";
                    default -> "";
                });

        String[] namesone = {"Anna", "Bob", "Carole", "David", "Ed", "Fred", "Gary"};
        String[] randomList = randomlySelectedValues(20, namesone,
                () -> new Random().nextInt(0, namesone.length));
        System.out.println(Arrays.toString(randomList));

        Supplier<String> iLoveJava = () -> "I love Java!";
        Supplier<String> iLoveJava2 = () -> {return "I love Java";};

        String supplierResult = iLoveJava.get();
        System.out.println("supplierResult = " + supplierResult);
        String[] names = {"Anna", "Bob", "Carole", "David", "Ed", "Fred", "Gary"};
        Arrays.stream(names).map(s->s.toUpperCase()).forEach(l -> System.out.printf(l + ", "));
        String[] names2 = {"Anna", "Bob", "Carole", "David", "Ed", "Fred", "Gary"};
        Arrays.setAll(names2, i -> names2[i].toUpperCase());
        System.out.println("names2 = " + Arrays.toString(names2));
        Arrays.stream(names).map(s-> "").forEach(l -> System.out.printf(l + ", "));

        List<String> backebyArray = Arrays.asList(names);
        backebyArray.replaceAll(s -> s += " " + getRandomChar('B', 'K') + ".");
        System.out.println("backebyArray = " + backebyArray);

        backebyArray.replaceAll(s -> s += " " + getReversedString(s.split(" ")[0]));
        System.out.println("backebyArray reverses = " + backebyArray);

        backebyArray.replaceAll(s -> getReversedString(s));
        System.out.println("backebyArray reversed again = " + backebyArray);

        Arrays.asList(names).forEach(name -> System.out.printf("%-10s", name));

        Random random = new Random();
        Arrays.setAll(names, s-> {
            //return " " + s + " " + (char) random.nextInt(62, 84) + ".";});
            return names[s] + " " + (char) random.nextInt((int)'A', (int)'Z'+1) + ".";});

        System.out.println("\n(nnames) = " + Arrays.toString(names));

        List<String> newList = new ArrayList<>(List.of(names));
        //newList.removeIf(s -> s.substring(0, s.indexOf(" ")).equals(getReversedString(s.substring(s.lastIndexOf(" ")+1))));
        newList.removeIf(s -> {
            String first = s.substring(0, s.indexOf(" "));
            String last = s.substring(s.lastIndexOf(" ")+1);
            return first.equals(last);
        });

        System.out.println("newList = " + newList);

        int[][] mtx = new int[][] { {1,2,3,4},  // Hero 0
                                    {5,6,7,8},  // Hero 1
                                    {9,10,11,12},
                                    {13,14,15,16}};

        int[][] hv = new int[][] {  {0,2,0,4},      // Hero 0
                                    {2,0,5,8},      // Hero 1
                                    {0,5,0,12},   // Hero 2
                                    {4,8,12,0}}; // Hero 3

        for (int[] row: mtx){
            //System.out.print("Hero =  " );
            for (int vil : row) {
                String formatted = "Vil%3d    ".formatted(vil);
                //System.out.print(formatted.toString());
            }
            //System.out.println();
        }
        for (int i=0; i< mtx[0].length; i++){
            //System.out.print("Hero =  " );
            for (int j=0; j <mtx.length; j++) {
                String formatted = "mat[%d][%d] Hero %3d Vil%3d =    ".formatted(i,j,i,j);
                //System.out.print(formatted.toString());
                //System.out.println("mtx[i][j] = " + mtx[i][j]);
            }
            //System.out.println();
        }
        for (int i=0; i< hv[0].length; i++){
            //System.out.print("Hero =  " );
            for (int j=0; j <hv.length; j++) {
                String formatted = "hv[%d][%d] Hero %3d Vil%3d =    ".formatted(i,j,i,j);
                //System.out.print(formatted.toString());
                //System.out.println("hv[i][j] = " + hv[i][j]);
            }
            //System.out.println();
        }

        List<Integer> ans = new ArrayList<>();
        if (mtx.length == 0) {
            return;
        }
        int m = mtx.length;
        int n = mtx[0].length;
        boolean[][] seen = new boolean[m][n];

        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int x = 0, y = 0, di = 0;

        for (int i = 0; i< m*n; i++) {
            ans.add(mtx[x][y]);
            seen[x][y] = true;
            int cr = x + dr[di];
            int cc = y + dc[di];
            if (0 <= cr && cr < m && 0 <= cc && cc < n && !seen[cr][cc]) {
                x = cr;
                y = cc;
            } else {
                di = (di + 1) % 4;
                x += dr[di];
                y += dc[di];
            }
        }
        ans.forEach(e -> System.out.printf("%d, ",e));
    }

    public static char getRandomChar(char startChar, char endChar) {
        return (char) new Random().nextInt((int)startChar, (int)endChar + 1);
    }

    public static String getReversedString(String firstName) {
        return new StringBuilder(firstName).reverse().toString();
    }
    public static String[] randomlySelectedValues(int cnt, String[] values, Supplier<Integer> s){
        String[] selectedValues = new String[cnt];
        for (int i = 0; i < cnt; i++) {
            selectedValues[i] = values[s.get()];
        }
        return selectedValues;
    }
    public static void lamvariety() {

        String aStr = "Hello";
        String bStr = "He".concat("llo");
        String cStr = String.join("l", "He","lo");
        String dStr = "hello";
        List<String> hellos = Arrays.asList(aStr, bStr,dStr, cStr);
        hellos.forEach(s -> System.out.print(s + ", "));

        Set<String> strHashSet = new HashSet<>(hellos);
        System.out.println("\nstrHashSet = " + strHashSet);

        for (String setVal : strHashSet) {
            System.out.print(setVal + ": ");
            for (int i=0; i<hellos.size(); i++) {
                if (setVal == hellos.get(i)) {
                    System.out.print(i+ ", ");
                }
            }
        }
        System.out.println();

        int randomInBetween = new Random().nextInt(10, 99);
        for (int i=0; i<5; i++) {
            System.out.println(" = " + new Random().nextInt(50));
        }

        long phone = 9452679109L;
        Set<String> phones = new HashSet<>();
        String p1 = String.valueOf(phone);
        if (phone > 0 ) {
            String p = String.valueOf(phone);
            p = "(%s) %s-%s".formatted(p.substring(0,3), p.substring(3,6), p.substring(6));
            if (!phones.add(p)) {
                System.out.printf("This phone is duplicate");
            }
            System.out.println("phone = " + p);
        }



        List<String> list = new ArrayList<>(List.of(
                "alpha", "bravo", "charlie", "delta"));

        for (String s : list){
            System.out.print(s + "  ");
        }
        System.out.printf("---------------\n");
        list.forEach((s) -> System.out.print(s + "  "));
        System.out.printf("---------------\n");
        list.forEach(System.out::println);
        System.out.printf("---------------\n");
        String prefix = "nato";
        list.removeIf(s->s.equalsIgnoreCase("bRavo"));
        list.forEach((var myStr) -> {
            char first = myStr.charAt(0);
            System.out.println(prefix + " " + myStr + " means " + first);
        });
        list.addAll(List.of("echo", "easy", "earnest"));
        list.forEach(System.out::println);
        list.removeIf(s->s.startsWith("ea"));
        list.forEach(s->System.out.println(s));
        String sentence = "My name is Lamda Expression";
        Arrays.stream(sentence.split(" ")).forEach(System.out::print);
        Arrays.stream(sentence.split(" ")).forEach(s->System.out.printf("%s - ", s));

        //Consumer<String> printWordsLamda = sentence -> {
        //    String[] parts = sentence.split(" ");
        //};
        UnaryOperator<String> everySecondChar = source -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i=0; i<source.length(); i++){
                if( i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };
        System.out.println(everySecondChar.apply(sentence.replaceAll(" ", "")));
        System.out.println(everySecondChar.apply("1234567890"));
    }
    static void lamdaexSecondLevel() {
        List<Person> people = new ArrayList<>(Arrays.asList(
                new Main.Person("Poly", "Robert"),
                new Person("Sally", "James"),
                new Person("Linus", "Brown"),
                new Person("Pepp", "Brown"),
                new Person("Charlie", "Robert")
        ));

        var comparatorLastName = new Comparator<Person>(){
            @Override
            public int compare(Person o1, Person o2) {
                return o1.lastName().compareTo(o2.lastName());
            }
        };

        people.sort((o1, o2) -> o1.lastName().compareTo(o2.lastName()));
        //people.sort(Comparator.comparing(Person::lastName));
        System.out.println("people = " + people);

        interface EnhancedComparator<T> extends Comparator<T> {
            int secondLevel(T o1, T o2);
        }

        var comparatorMixed = new EnhancedComparator<Person>() {

            @Override
            public int compare(Person o1, Person o2) {
                int result = o1.lastName().compareTo(o2.lastName());
                return (result == 0 ? secondLevel(o1,o2) : result);
            }

            @Override
            public int secondLevel(Person o1, Person o2) {
                return o1.firstName().compareTo(o2.firstName());
            }
        };

        people.sort(comparatorMixed);
        System.out.println("people = " + people);
    }
}

package com.dcp.portone.corejava.file;

import com.dcp.portone.leet.Operation;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

//        Identification of Static Members from top to bottom.
//        Execution of Static variable assignment and a Static block from top to bottom.
//        Execution of the main method.
//        Identification of Instance Members from top to bottom.
//        Execution of Instance variable assignment and Instance block from top to bottom.
//        Execution of Constructor.
// https://en.wikipedia.org/wiki/Functional_programming
// Consumer void accept(T t)   // Function R apply(T t) // Predicate boolean test(T t) // Supplier T get() Interface Categories
// A functional interface can have one only one abstract method --- lambda infers this to derive the parameters and return type
// Also called SAM (Single Abstract Method)  which is called functional method, again which is the target type for lambda

public class LambdaBasics {
    record Person(String firstName, String lastName) {
        @Override
        public String toString() {
            return firstName + " " +  lastName;
        }
    }
    public static <T> T calculator(Operation<T> function, T value1, T value2) {
        T result = function.operate(value1, value2);
        System.out.println("Result of operation: " + result);
        return result;
    }

    public static<T> void processPoint(T t1, T t2, BiConsumer<T, T> consumer) {
        consumer.accept(t1, t2);
    }
    public static String[] randomlySelectedValues (int cnt, String[] str, Supplier<Integer> s) {
        String[] selectedValues = new String[cnt];
        for (int i=0; i< cnt; i++) {
            selectedValues[i] = str[s.get()];
        }
        return selectedValues;
    }
    public static int[] getRandomNums(int cnt, int bound) {
        Random random = new Random();
        int[] newArr = new int[cnt];
        for(int i=0; i<cnt; i++) {
            newArr[i] = random.nextInt(bound+1);
        }
        return newArr;
    }

    public static void sortDesc(int c) {

        int[] arr = getRandomNums(c, 1000);
        System.out.println(Arrays.toString(arr));
        boolean flag = true;
        int temp;
        while (flag) {
            flag = false;
            for (int i = 0; i< arr.length-1; i++) {
                if(arr[i] < arr[i+1]) {
                    temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    flag = true;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static int[] reverseArray() {
        int[] myar = getRandomNums(3, 100);
        System.out.println(Arrays.toString(myar));
        int[] revarr = new int[myar.length];
        int index = 0;
        //for(int i=myar.length-1; i>0; i--) {
        //    revarr[index++] = myar[i];
        //}
        for(int i=0; i< myar.length/2; i++) {
            int temp = myar[i];
            //revarr[i] = myar[myar.length-1-i];
            //revarr[myar.length-1-i] = myar[i];
            myar[i] = myar[myar.length-1-i];
            myar[myar.length-1-i] = temp;
        }
        System.out.println(Arrays.toString(myar));
        return  revarr;
    }

    public static int[] readNums(int cnt) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter int numbers separated by ,. Q to Quit. " + cnt);
        System.out.println();
        String input = scanner.nextLine();
        String[] intparts = input.split(",");
        int[] arr = new int[cnt];
        for (int i=0; i< cnt; i++) {
            arr[i] = Integer.parseInt(intparts[i].trim());
        }
        //while (str.charAt(0) != 'Q' || str.charAt(0) != 'q') {
        //for (int i=0; i<cnt; i++) {
        //    arr[i] = scanner.nextInt();
        //}
        //}

        System.out.println(Arrays.toString(arr));

        return arr;
    }

    public static Scanner scanner = new Scanner(System.in);
    public static void displayMenu() {
        System.out.println("Menu: 0 to quit, 1 to add 2 to delete:");
    }
    public static void addItems(ArrayList groceries) {
        System.out.println("Enter grocery list [comma sepered list]");
        String[] items = scanner.nextLine().split(",");
        Arrays.asList(items).replaceAll(String::trim);
        for (String tr : items) {
            //String tr = st.trim();
            if (!groceries.contains(tr)) {
                System.out.println("Adding item " + tr);
                groceries.add(tr);
            }
        }
        //groceries.addAll(Arrays.asList(items));

        groceries.sort(Comparator.naturalOrder());
        System.out.println(groceries);
    }
    public static void deleteItems(ArrayList groceries) {
        System.out.println("Enter grocery list [comma sepered list]");
        String[] items = scanner.nextLine().split(",");
        for (String l : items) {
            if (groceries.contains(l.trim())) {
                System.out.println("Removing " + l);
                groceries.remove(l);
            }
        }
        groceries.sort(Comparator.naturalOrder());
        System.out.println(groceries);
    }
    public static void getGroceryListFromConsole() {
        boolean flag = true;
        ArrayList<String> groceries = new ArrayList<>();
        while (flag) {
            displayMenu();
            switch (Integer.parseInt(scanner.nextLine())) {
                case 1 -> addItems(groceries);
                case 2 -> deleteItems(groceries);
                default -> flag = false;
            }
        }
    }
    //if (flier instance of Trackable tracked) {
    //    tracked.track();
    //}

    public static void testIterator(LinkedList<String> list) {
        var iterator = list.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(list);
    }

    public static void main(String[] args) {

        getGroceryListFromConsole();

        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Lucy", "Van Pelt"),
            new Person("Sally", "Brown"),
            new Person("Linus", "Van Pelt"),
            new Person("Peppermint", "Patty"),
            new Person("Charlie", "Brown")
        ));

        List<String> list = new ArrayList<String>(List.of("alpha", "bravo", "charlie", "delta"));

        String[] parts = "This is multi part stmt".split(" ");
        Arrays.asList(parts).forEach(s -> System.out.println(s));

        Arrays.asList("This is a multi part stmt".split(" ")).forEach(s -> System.out.println(s));

        // Using anonymous class
        var comparatorLastName = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.lastName().compareTo(o2.lastName());
            }
        };

        people.sort((o1, o2) -> o1.lastName().compareTo(o2.lastName()));
        System.out.println(people);

        people.sort(Comparator.comparing(Person::firstName));
        System.out.println(people);

        interface EnhancedComparator<T> extends Comparator<T> {
            int secondLevel(T o1, T o2);
        }

        var comparatorMixed = new EnhancedComparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                int result =  o1.lastName().compareTo(o2.lastName);
                return (result == 0) ? secondLevel(o1, o2) : result;
            }
            @Override
            public int secondLevel(Person o1, Person o2) {
                return o1.firstName().compareTo(o2.firstName());
            }
        };

        people.sort(comparatorMixed);
        System.out.println(people);
        
        for (String s: list) {
            System.out.printf("%s, ",s);
        }

        list.forEach(s -> System.out.printf("%s, ",s));
        System.out.println("----------------");
        list.forEach((var s) -> {
            char first = s.charAt(0);
            System.out.println(first + " means " + s);
        });

        int result1 = calculator((a,b) -> a+b, 5, 4);
        double result2 = calculator((a,b) -> a*b, 5.2, 4.4);
        String str = calculator((s1, s2) -> s1.toUpperCase() + s2.toUpperCase(), "My Name is ", "Nagesh!!!");

        //String[] randomList = randomlySelectedValues(15, names, () -> new Random().nextInt(0,names.length));
        var coorods = Arrays.asList(
                new Double[] {12.0346, 17.4546},
                new Double[] {13.5546, 13.7843},
                new Double[] {2.8934, 19.1743});

        coorods.forEach(s -> System.out.println(Arrays.toString(s)));
        BiConsumer<Double, Double> p1 = (lat, lon) -> System.out.printf("[Lat:%.3f Lon:%.3f]%n", lat, lon);
        //var point1 = coorods.get(0);
        //processPoint(point1[0], point1[1], p1);  List.of() Arrays.asList() sort(Comparator.naturalOrder()//reverseOrder())
        coorods.forEach(s -> processPoint(s[0], s[1], p1));
        List<String> list1 = new ArrayList<>(List.of("Ann", "Bob", "Carol", "David", "Ed", "Fred"));
        String[] names = {"Ann", "Bob", "Carol", "David", "Ed", "Fred"};
        List<String> myll = Arrays.asList(names); // List.of(names);
        //myll.removeIf(n -> n.equalsIgnoreCase("david"));
        ArrayList<String> mylll = new ArrayList<>(myll);
        mylll.removeIf(n -> n.equalsIgnoreCase("Bob"));
        mylll.forEach(s -> System.out.printf("%s, ", s));
        System.out.println("-".repeat(30));
        List<String> myl = Arrays.stream(names).collect(Collectors.toList());
        list1.removeIf(n -> n.equalsIgnoreCase("david"));
        myl.removeIf(n -> n.equalsIgnoreCase("eD"));

        list1.forEach(s -> System.out.printf("%s, ", s));
        System.out.println("-".repeat(30));
        myl.forEach(s -> System.out.printf("%s, ", s));
        System.out.println("-".repeat(30));
        myl.addAll(List.of("echo", "ersy", "easyrnet"));
        myl.removeIf(s -> s.startsWith("sy", 2));
        myl.forEach(s -> System.out.printf("%s, ", s));
        var mylArray = myl.toArray(new String[myl.size()]);
        System.out.println("-".repeat(30) + "below is mylArray");
        System.out.printf("%s, ", Arrays.toString(mylArray));
        System.out.println("-".repeat(30));
        String[] emptyStr = new String[10];
        Arrays.fill(emptyStr, "AA");
        Arrays.setAll(emptyStr, i-> "" + (i*5) + ". " +
                switch (i) {
                    case 0 -> "Zero";
                    case 1 -> "One";
                    case 2 -> "Two";
                    case 3 -> "Three";
                    case 5 -> "Five";
                    case 6 -> "Six";
                    case 8 -> "Nine";
                    default -> "SPECIAL";

            });

        ArrayList<String> stringList = new ArrayList<>(List.of("Jan", "Feb", "Mar"));
        String[] stringArray = stringList.toArray(new String[0]);
        System.out.println("Arrays.toString(emptyStr) = " + Arrays.stream(emptyStr).toList() ); //+ Arrays.toString(emptyStr));

        String[] randomList = randomlySelectedValues(15, names, ()-> new Random().nextInt(0,names.length));

        System.out.println(Arrays.toString(randomList));

        /*Consumer<String> printWordsLambda = sentence -> {
            //String[] partsw = sentence.split(" ");
            Arrays.asList(sentence.split(" ")).forEach(s-> System.out.println(s));
            };*/
        Consumer<String> printWordsLambda = sentence -> Arrays.asList(sentence.split(" ")).forEach(s-> System.out.println(s));
        printWordsLambda.accept("This is a multi part stmt");

        sortDesc(8);
        reverseArray();
        readNums(5);
    }
}

//@FunctionalInterface
//public interface Operation<T> {
//    T operate(T value1, T value2);
//}
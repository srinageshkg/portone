package com.dcp.portone.designpatterns;

import lombok.ToString;

import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

class InvalidUsageException extends Exception{
    public InvalidUsageException(String m){
        super("Calling Constructor of User Defined Exception..."+m);
    }
}

// Checked Exceptions checked at compile time IOException ClassNotFoundException
// UnChecked Exceptions are checked at runtime child classes or RunTime Exception Arithmetic, NullPointerException, NoSuchElementException
// try catch finally throw throws
@ToString
public class StringOperations {

    public static void main(String[] args) {
        sbex();regexp();

    }

    public static void sbex() {
        String[] names = new String[] {"Nagesh Kumar", "Cosmin Desai Ph.D.", "Srnagesh Ganji M.D.", "Nagesh Kumar M.Tech",
                "Amit Kumar", "Kumar Srinagesh M.D.", "Amrita Desai Ph.D."};

        List<StringBuilder> population = getNames(names);

        System.out.println("population = " + population);

        Map<StringBuilder, Integer> counts = new TreeMap<>();  // sorted by name
        population.forEach( s -> {
            counts.merge(s, 1, Integer::sum);
        });
        System.out.println("counts = " + counts);

        StringBuilder sriganmd = new StringBuilder("Srnagesh Ganji M.D");
        System.out.println("There are " + counts.get(sriganmd) + " records for " + sriganmd);

        List<StringBuilder> standardizedNames = standardizeNames(population);
        System.out.println("standardizedNames = " + standardizedNames);

        counts.forEach((k,v) -> System.out.println(k + " : " + v));
        counts.keySet().forEach(k -> System.out.println(k + " : " + counts.get(k)));

        PersonKid[] kids = null;
        System.out.println("personKids = " + toStr(kids));

    }

//    public class PersonKid(String name, String dob, PersonKid[] kids) {
    public static void regexp() {

        String sen1 = "Hello, world!.";
        String chal2a = "[A-Z].*\\."; // starts with upper case letter ends with \\. with some char in the middle .*
        String chal2b = "[A-Z][a-z\\s]+[.]"; // starts with upper case letter ends with \\. with some char in the middle .*
        String chal3a = "^[A-Z][\\p{All}+[!?.]$"; // starts with upper case letter ends with \\. with some char in the middle .*
        String chal3b = "[A-Z][a-z\\s]+[\\p{Punct}[!?.]"; // starts with upper case letter ends with \\. with some char in the middle .*
        boolean matches = sen1.matches("Hello, world!");
        boolean matched = Pattern.matches("[A-Z].*+[.]", sen1);

        Pattern firstPattern = Pattern.compile("[A-Z].*[.]");
        var macher = firstPattern.matcher(sen1);
        System.out.println(macher.matches() + ": " + sen1);
        Pattern.compile("<[hH](?<level>\\d)>(.*)</[hH]\\d>");

        while (macher.find()) {
            System.out.println(macher.group("level") + "  " + macher.group(2));
            System.out.println("index =   " + macher.start("level"));
        }
        macher.reset();
        macher.results();

        System.out.println("CWD is = " + new File("").getAbsolutePath() + " " + File.listRoots());

    }

    public static String toStr (PersonKid[] kids) {

        PersonKid son = new PersonKid("Akhil", "1/1/2000");
        PersonKid daut = new PersonKid("Shri", "1/1/2005");

        if (kids == null) {
//            PersonKid[] fatherkids = new PersonKid[]{son, daut};
            PersonKid[] fkids = {son, daut};
            kids = fkids;
        }

        PersonKid[] kidscopy = Arrays.copyOf(kids, kids.length -1);

        System.out.println("kidscopy copyOf = " + Arrays.asList(kidscopy));

        PersonKid father = new PersonKid("Nagesh", "1/1/1900", kids);

        System.out.println("father = " + father);

//        String kidString = "n/a";
//        if (kids != null) {
//            String[] names = new String[kids.length];
//            PersonKid[] finalKids = kids;
//            Arrays.setAll(names, i-> names[i] = finalKids[i] == null ? "" : finalKids[i].getName());
//            kidString = String.join(", ", names);
//        }
        return father.toString();
    }
    public static  List<StringBuilder> getNames(String[] names) {
        List<StringBuilder> list = new ArrayList<>(names.length);
        int index = 3;

        for (String name: names) {
            for (int i=0; i<index; i++) {
                list.add(new StringBuilder(name));
            }
            index++;
        }
        return list;
    }

    public static List<StringBuilder> standardizeNames(List<StringBuilder> list) {
        List<StringBuilder> newList = new ArrayList<>();

        for (var name: list) {
            StringBuilder tempName = new StringBuilder(name);
            for (String suffix: new String[] {"Ph.D.", "M.D.", "M.Tech"}) {
                int startIndex = -1;
                if ((startIndex = name.indexOf(suffix)) != -1) {
                    tempName.replace(startIndex - 1, startIndex+suffix.length(), "");
                }
            }
            newList.add(tempName);
        }
        return newList;
    }
    static void message() throws InvalidUsageException, ArithmeticException {
        int[] arr; arr = new int[5];
        ArrayList count1 = new ArrayList<>(Arrays.asList(23,21,56,43,56));

        if (count1.size() > 5) {
            throw new InvalidUsageException("Array size is greater than 5.");
        }
        try {
            if (count1.size() != 5) {
                throw new InvalidUsageException("Before ArithmeticException..");
            }
        } catch (ArithmeticException ex){
            System.out.println(" Caught ArithmeticException = " + ex.getMessage());
        } finally {
            System.out.println(" Finally a = 5" );
        }
        HashSet<Integer> set = new HashSet<>();
        set.addAll(count1);
        System.out.println("ArrayList is: " + count1 + "  HashSet is: " + set);
        //Iterator itr = set.iterator();
        //while (itr.hasNext()) {
        //    System.out.println("HashSet is: " + itr.next());
        //}
        TreeSet<Integer> sortedSet = new TreeSet<>(set);
        System.out.println("sortedSet is: " + sortedSet);
    }

    public void examples() {
        try {
            String str = "AbstractFactory";
            StringBuffer str1 = new StringBuffer();  // Thread safe?  Volatile
            str1.append(str);
            try {
                message();
            } catch (InvalidUsageException ex){
                System.out.println("Calling InvalidUsageException..."+ ex.getMessage());
            }

            Queue<String> queue = new LinkedList<>();
            queue.add("Cat"); queue.add("Dog"); queue.add("Rat"); queue.add("Cat");
            System.out.println("Queue - LinkedList is: " + queue);
            System.out.println("Queue - contains Cat? : " + queue.contains("Cat"));
            queue.remove("Cat");
            System.out.println("Queue - LinkedList is: " + queue);

            Queue<String> priqu = new PriorityQueue<>(queue);
            priqu.add("Rabbit"); priqu.add("Monkey"); priqu.add("Donkey"); priqu.add("Chimp"); priqu.add("Cheeta");
            System.out.println("Queue - PriorityQueue is: " + priqu);

            Deque<Integer> deque = new ArrayDeque<>();
            deque.add(12); deque.addFirst(8); deque.addLast(20); deque.addFirst(14); deque.offerFirst(4);
            System.out.println("Deque - ArrayDeque is: " + deque);

        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException"+e);
        } catch (IndexOutOfBoundsException e){
            System.out.println("IndexError"+e);

        } catch (Exception e) {
            System.out.println("Exception"+e);
        } finally {
            //System.out.println("Finally you are here");
        }
    }

}

class PersonKid {
    private String name;
    private String dob;
    private PersonKid[] kids;
    public PersonKid(String name, String dob, PersonKid[] kids) {
        this.name = name;
        this.dob = dob;
        this.kids = kids;
    }
    public PersonKid(String name, String dob) {
        this(name, dob, null);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String kidString = "n/a";
        if (kids != null) {
            String[] names = new String[kids.length];
            Arrays.setAll(names, i-> names[i] = kids[i] == null ? "" : kids[i].name);
            kidString = String.join(", ", names);
        }
        return "%s ,dob = %s has kids %s".formatted(name, dob, kidString);
    }
}
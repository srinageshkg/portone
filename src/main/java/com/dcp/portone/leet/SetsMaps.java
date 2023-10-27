package com.dcp.portone.leet;

import lombok.ToString;

import java.util.*;

record Place (String name, int distance) implements Comparable {
    @Override
    public String toString() {
        //String.format("%s (%d)", name, distance);
        return "%s (%d)".formatted(name, distance);
    }

    @Override
    public int compareTo(Object o) {
        Place other = (Place) o;
        return this.name.compareTo(other.name);
    }
}

record Customer (String name, ArrayList<Double> transactions) {
    public Customer(String name, double initialDeposit) {
        this(name.toUpperCase(), new ArrayList<Double>(100));
        transactions.add(initialDeposit);
    }

    @Override
    public String toString() {
        return "%n%-15s %-10s".formatted(name, Arrays.asList(transactions));

    }
}
@ToString
public class SetsMaps {
    String blockText = """
            ArrayList on top of Array. LinkedList is a doubly Linked List
            ArrayList and LinkedList implement all of Array methods. Linked list also implements the Queue and Stack methods
            Queue FIFO single ended and double ended - LinkedList double ended list 
            Stack LIFO
            """;
}

class LinkedLists {
    public enum DayOfTheWeek {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;

        public double getSomething() {
            return switch (this) {  // this as part of the enum only
                case SUNDAY -> 0.00;
                case MONDAY -> 1.00;
                case FRIDAY -> 5.00;
                case TUESDAY -> 2.00;
                case WEDNESDAY -> 3.00;
                case THURSDAY -> 4.00;
                case SATURDAY -> 0.00;
                default -> 100.00;
            };
        }
    }

    // MonthInTheYear
    public enum SetOfSizes {
        EXTRA_SMALL, SMALL, MEDIUM, LARGE, EXTRA_LARGE
    }
    public static String LinkedListActions = """
                 Available Actions (select word or letter):
                 (F)orward
                 (B)ackward
                 (L)ist Places
                 (M)enu
                 (Q)uit""";

    public void main() {
        DayOfTheWeek weekDay = getRandomDay();
        System.out.println(weekDay.name() + " name and ordinal " + weekDay.ordinal());
        switchDayOfTheWeek(weekDay);
        for (DayOfTheWeek day: DayOfTheWeek.values()) {
            System.out.println(day.name() + " Brings " + day.getSomething());
        }
        Customer bob = new Customer("Bob Wra", 1000);
        System.out.println(bob);

        Bank bank = new Bank("Barclays");
        bank.addNewCustomer(bob);
        bank.addNewCustomer("Jane A", 500);
        bank.addNewCustomer("Larry James", 2500);
        bank.addTransaction("Jane A", 1300);
        bank.addTransaction("Larry James", 5800);
        bank.addTransaction("Larry James", -1000);
        bank.addTransaction("Larry James", -101.05);

        bank.printStatement("Larry James");

        System.out.println(bank);

        Integer[] wrapperArray = new Integer[5];
        wrapperArray[0] = 50;
        System.out.println("wrapperArray = " + Arrays.toString(wrapperArray));

        Character[] characterArray = {'a', 'e', 'i', 'o', 'u'};
        System.out.println("Arrays.toString(characterArray) = " + Arrays.toString(characterArray));

        ArrayList<Album> albums = new ArrayList<>();

        Album album = new Album("Stormbringer", "Deep Purple");
        album.addSong("Stormbringer", 4.6);
        album.addSong("Love don't mean a thing", 4.22);
        album.addSong("Holy man", 4.3);
        album.addSong("Hold on", 5.6);
        album.addSong("Lady double dealer", 3.21);
        album.addSong("You can't do it right", 6.23);
        album.addSong("High ball shooter", 4.27);
        album.addSong("The gypsy", 4.2);
        album.addSong("Soldier of fortune", 3.13);

        albums.add(album);
        album = new Album("For those about to rock", "AC/DC");
        album.addSong("For those about to rock", 5.44);
        album.addSong("I put the finger on you", 3.25);
        album.addSong("Lets go", 3.45);
        album.addSong("Inject the venom", 3.33);
        album.addSong("Snowballed", 4.51);
        album.addSong("Evil walks", 3.45);
        album.addSong("C.O.D.", 5.25);
        album.addSong("Breaking the rules", 5.32);
        album.addSong("Night of the long knives", 5.12);
        albums.add(album);

        //album.printAlbum(albums);
        // Nagesh

        Integer five = 5;
        Integer[] others = {0,5,10,-50,50};

        for (Integer i: others) {
            int val = five.compareTo(i);
            System.out.printf("%d %s %d: compareTo=%d%n", five,
                    (val == 0 ? "==" : (val < 0 ) ? "<" : ">"), i, val);

            System.out.println("val = " + five.equals(i));
        }
        String banana = "banana";
        String[] fruit = {"apple","banana","pear","Banana","Apple"};

        for (String s: fruit) {
            int val = banana.compareTo(s);
            System.out.printf("%s %s %s: compareTo=%d%n", banana,
                    (val == 0 ? "==" : (val < 0 ) ? "<" : ">"), s, val);

            System.out.println("val = " + banana.equals(s));
        }
    }


    public DayOfTheWeek getRandomDay() {
        int randomNumber = new Random().nextInt(7);
        var allDays = DayOfTheWeek.values();
        return allDays[randomNumber];
    }

    public void switchDayOfTheWeek(DayOfTheWeek weekDay) {
        int weekDayInteger = weekDay.ordinal() + 1;
        switch (weekDay) {   // try switch (this) as part of the enum class
            case SUNDAY -> System.out.println("Sunday is Day = " + weekDayInteger);
            case TUESDAY -> System.out.println("Tuesday is Day = " + weekDayInteger);
            default -> System.out.println(weekDay.name().charAt(0) +
                    weekDay.name().substring(1).toLowerCase() +
                    " day is Day " + weekDayInteger);
        }
    }
    public void mainPlaces() {
        //LinkedList<Place> placesToVisit = new LinkedList<>();
        var placesToVisit = new LinkedList<Place>();

        Place adelaide = new Place("Adelaide", 1374);
        addPlace(placesToVisit, adelaide);
        //System.out.println("placesToVisit = " + placesToVisit);
        addPlace(placesToVisit, new Place("Adelaide", 1374));
        addPlace(placesToVisit, new Place("Brisbane", 917));
        addPlace(placesToVisit, new Place("Perth", 3923));
        addPlace(placesToVisit, new Place("Alice Springs", 2771));
        addPlace(placesToVisit, new Place("Darwin", 3972));
        addPlace(placesToVisit, new Place("Melbourne", 877));
        placesToVisit.addFirst(new Place("Sidney", 0));
        System.out.println("placesToVisit = " + placesToVisit);

        Arrays.sort(placesToVisit.toArray());
        System.out.println("placesToVisit After = " + placesToVisit);

        var iterator = placesToVisit.listIterator();
        Scanner scanner = new Scanner(System.in);
        boolean quitLoop = false;
        boolean forward = true;

        System.out.println(LinkedListActions);

        while (!quitLoop) {
            if (!iterator.hasPrevious()) {
                System.out.println("Originating = " + iterator.next());
                forward = true;
            }

            if (!iterator.hasNext()) {
                System.out.println("Final Destination = " + iterator.previous());
                forward = false;
            }
            System.out.printf("Enter Value: ");

            String menuItem = scanner.nextLine().toUpperCase().substring(0,1);

            switch (menuItem) {
                case "F" -> {
                    if (!forward) {
                        forward = true;
                        if (iterator.hasNext()) {
                            iterator.next(); // Adjust the Position
                        }
                    }
                    System.out.printf("User wants to go forward -> ");
                    if (iterator.hasNext()) {
                        System.out.println(iterator.next());
                    }
                    break;
                }
                case "B" -> {
                    if (forward) {
                        forward = false;
                        if (iterator.hasPrevious()) {
                            iterator.previous(); // Adjust the Position
                        }
                    }
                    System.out.printf("User wants to go backwords ->");
                    if (iterator.hasPrevious()) {
                        System.out.println(iterator.previous());
                    }
                    break;
                }
                case "M" -> {
                    System.out.printf(LinkedListActions); break;
                }
                case "L" -> {
                    System.out.println(placesToVisit); break;
                }

                default -> {quitLoop = true; break;}
            }
        }

    }

    private void addPlace(LinkedList<Place> list, Place place) {
        // record validate the entre Object equality
        if (list.contains(place)) {
            System.out.printf("Place already exists: " + place);
            return;
        }
        // Compare only City Name
        for (Place pla: list) {
            if (pla.name().equalsIgnoreCase(place.name())) {
                System.out.printf("Place already exists: " + place);
                return;
            }
        }
        //
        int matchIndex = 0;
        for (var listPlace: list) {
            if (place.distance() < listPlace.distance()) {
                list.add(matchIndex, place);
                System.out.println("listPlace = " + matchIndex + "  " + listPlace);
                return;
            }
            matchIndex++;
        }

        // Place holder to add the element at the end
        list.add(place);

    }
    // calling from funcprog.java main

    public void mainOne() {
        //LinkedList<String> placesToVisit = new LinkedList();
        var placesToVisit = new LinkedList<String>(); // var is used to change the Type LinkedList or Collection
        placesToVisit.add("Sydney");
        placesToVisit.add(0,"Canberra");
        addMoreElements(placesToVisit);
        System.out.println("placesToVisit = " + placesToVisit);
        //removeElements(placesToVisit);
        System.out.println("placesToVisit = " + placesToVisit);
        gettingElements(placesToVisit);
        printItenerary(placesToVisit);
        printItenerary3(placesToVisit);
        testIterator(placesToVisit);

    }

    public void addMoreElements(LinkedList<String> list) {
        list.addFirst("Darwin");
        list.addLast("Hobart");
        // QueueMethods
        list.offer("Melbourne");
        list.offerFirst("Brisbane");
        //Stack
        list.push("Alice Spring");
    }
    private void removeElements(LinkedList<String> list) {
        list.remove(4);
        list.remove("Brisbane");

        String s1 = list.remove();
        System.out.println("First s1 removed = " + s1);

        String s2 = list.removeLast();
        System.out.println("Last s2 removed = " + s2);

        // Queue / Deque poll methods
        String p1 = list.poll();  //pollFirst
        System.out.println("First p1 removed = " + p1);

        String p2 = list.pollLast();
        System.out.println("Last p2 removed = " + p2);

        list.push("Sydney");
        list.push("Darwin");
        System.out.println("list = " + list);
        String p3 = list.pop();
        System.out.println("p3 pop removed = " + p3); // removes First Element
        list.push("Sydney");
        list.offer("Melbourne");
        list.addFirst("Darwin");
    }

    public void gettingElements(LinkedList<String> list) {
        System.out.println("Retrieved list.get(4) = " + list.get(4));
        System.out.println("Get First " + list.getFirst());
        System.out.println("Get Last " + list.getLast());
        System.out.println("Get Last Index of Sydney " + list.lastIndexOf("Sydney"));
        System.out.println("Get First Index of Melbourne " + list.indexOf("Melbourne"));

        // Queue retrieval method  FIFO
        System.out.println("Element from Element() " + list.element());
        // Stack retrieval method LIFO
        System.out.println("Get Peek (First) " + list.peek());
        System.out.println("Get Peek Last " + list.peekLast());
    }

    public void printItenerary(LinkedList<String> list) {
        System.out.println("Itenerary 1 Trip Starts at " + list.getFirst());
        for (int i=1; i< list.size()-2 ; i++) {
            System.out.println(" --> From : " + list.get(i-1) + " to : " + list.get(i) );
        }
        System.out.println("Trip Ends at " + list.getLast());
    }

    public void printItenerary2(LinkedList<String> list) {
        System.out.println("Itenerary 2 Trip Starts at " + list.getFirst());
        String previousTown = list.getFirst();
        for (String town : list) {
            System.out.println(" --> From : " + previousTown + " to : " + town );
            previousTown = town;
        }
        System.out.println("Trip Ends at " + list.getLast());
    }
    public void printItenerary3(LinkedList<String> list) {
        System.out.println("Itenerary 3 Trip Starts at : " + list.getFirst());
        String previousTown = list.getFirst();
        ListIterator<String> iterator = list.listIterator(1);
        while (iterator.hasNext()) {
            var town = iterator.next();
            System.out.println(" --> From : " + previousTown + " to : " + town );
            previousTown = town;
        }
        System.out.println("Trip Ends at " + list.getLast());
    }

    public void testIterator(LinkedList<String> list) {
//        var iterator = list.iterator();
        var iterator = list.listIterator();
        while (iterator.hasNext()) {
//            System.out.println("iterator.next() = " + iterator.next());
            if (iterator.next().equals("Brisbane")) {
                iterator.add("Lake Wivenhoe");   // not on the list
            }
        }
        while (iterator.hasPrevious()) {
            System.out.println("Previous = " + iterator.previous());
        }
        while (iterator.hasNext()) {
//            System.out.println("iterator.next() = " + iterator.next());
            if (iterator.next().equals("Brisbane")) {
                iterator.add("Lake Wivenhoe");   // not on the list
            }
        }
        var iterator2 = list.listIterator(4);
        System.out.println("iterator2.next(index 4) = " + iterator2.next() + " Previous: " + iterator2.previous());
        System.out.println("list = " + list);
    }
}

class Bank {
    String name;
    private ArrayList<Customer> customers = new ArrayList<>(500);

    public Bank(String name) {
        this.name = name;
    }

    private Customer getCustomer(String customerName) {
        for (var cust: customers) {
            if (customerName.equalsIgnoreCase(cust.name())) {
                return cust;
            }
        }
        System.out.printf("Customer (%s) wans't found in %s Bank %n", customerName, name);
        return null;
    }

    public void addNewCustomer(String custName, double initialDeposit) {
        if (getCustomer(custName) == null) {
            this.customers.add(new Customer(custName, initialDeposit));
        }
    }
    public void addNewCustomer(Customer customer) {
        if (getCustomer(customer.name()) == null) {
            this.customers.add(customer);
        }
    }

    public void addTransaction(String name, double transactionAmt) {
        Customer cust = getCustomer(name);
        if (cust != null) {
            cust.transactions().add(transactionAmt);
        }
    }

    public void printStatement(String name) {
        Customer cust = getCustomer(name);
        if (cust != null) {
            System.out.println("Customer : " + cust.name());
            for (double d: cust.transactions()) {
                System.out.printf("$%10.2f (%s)%n", d, d > 0 ? "Credit" : "Debit");
            }
            //cust.transactions().stream().map(d -> d > 0).reduce(0d,(Sum));
        }
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", customers=" + customers +
                '}';
    }
}

class Album {
    public static String muuAlbum = """
            TIP:  In Album, use the findSong() method in addSong() and addToPlayList(String, LinkedList) to check if a song exists before proceeding.       
            TIP:  Be extremely careful with the spelling of the names of the fields, constructors and methods.
            TIP:  Be extremely careful about spaces and spelling in the returned String from the toString() method.
            SAMPLE INPUT
                    
            ArrayList<Album> albums = new ArrayList<>();
            Album album = new Album("Stormbringer", "Deep Purple");
            album.addSong("Stormbringer", 4.6);
            album.addSong("Love don't mean a thing", 4.22);
            album.addSong("Holy man", 4.3);
            album.addSong("Hold on", 5.6);
            album.addSong("Lady double dealer", 3.21);
            album.addSong("You can't do it right", 6.23);
            album.addSong("High ball shooter", 4.27);
            album.addSong("The gypsy", 4.2);
            album.addSong("Soldier of fortune", 3.13);
            albums.add(album);
            album = new Album("For those about to rock", "AC/DC");
            album.addSong("For those about to rock", 5.44);
            album.addSong("I put the finger on you", 3.25);
            album.addSong("Lets go", 3.45);
            album.addSong("Inject the venom", 3.33);
            album.addSong("Snowballed", 4.51);
            album.addSong("Evil walks", 3.45);
            album.addSong("C.O.D.", 5.25);
            album.addSong("Breaking the rules", 5.32);
            album.addSong("Night of the long knives", 5.12);
            albums.add(album);
            LinkedList<Song> playList = new LinkedList<Song>();
            albums.get(0).addToPlayList("You can't do it right", playList);
            albums.get(0).addToPlayList("Holy man", playList);
            albums.get(0).addToPlayList("Speed king", playList);  // Does not exist
            albums.get(0).addToPlayList(9, playList);
            albums.get(1).addToPlayList(3, playList);
            albums.get(1).addToPlayList(2, playList);
            albums.get(1).addToPlayList(24, playList);  // There is no track 24""";
    private String name;
    private String artist;
    private ArrayList<Song> songs;

    public Album(String name, String artist) {
        this(name.toUpperCase(), artist,null);
    }
    public Album(String name, String artist, ArrayList<Song> songs) {
        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList<Song>(100);
        if (songs != null) {
            this.songs.addAll(songs);
        }
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", songs=" + songs +
                '}';
    }

    public void printAlbum(ArrayList<Album> albums) {
        for (Album al: albums) {
            System.out.printf("%-25s - %-20s%n", al.name, al.artist);
            System.out.println("--------------------------------------");
            for (Song s:al.getSongs()) {
                System.out.println(s.toString());
            }
        }
    }
    boolean addSong(String title, double duration) {
        boolean status = false;
        Song newSong = new Song(title, duration);
        if (findSong(title) == null) {
            status = this.songs.add(new Song(title,duration));
        }
        return status;
    }
    Song findSong(String title){
        for (Song song: this.songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        return null;
    }
    boolean addToPlayList(int trackingNo, LinkedList<Song> playList) {
        boolean status = false;


        return status;
    }
    boolean addToPlayList(String title, LinkedList<Song> playList) {
        boolean status = false;

        return status;
    }
}

class Song {
    private String title;
    private double duration;

    public Song(String title, double duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "%-28s: %f".formatted(title , duration);
    }
}

class MobilePhone {
    private String myNumber;

    public String getMyNumber() {
        return myNumber;
    }

    private ArrayList<Contact> myContacts;

    public MobilePhone(String myNumber) {
        this(myNumber, null);
    }

    public MobilePhone(String myNumber, ArrayList<Contact> myContacts) {
        this.myNumber = myNumber;

        this.myContacts = new ArrayList<>(myContacts.size());
        myContacts.addAll(myContacts);
    }

    public void addNewContact(Contact contact) {

    }
    public boolean updateContact(Contact OldContact, Contact newContact) {
        return false;
    }

    public boolean updateContact(Contact contact) {
        return false;
    }

    public int removeContact(Contact contact) {
        return 0;
    }

    public int findContact(Contact contact) {
        return 0;
    }

    public int findContact(String contact) {
        return 0;
    }
    public Contact queryContact(String contact) {
        Contact c =  new Contact("Name");
        return c;
    }
    public void printContact() {
        for (Contact c : myContacts) {
            String formated = "%-15s -> %-10s".formatted(c.getName(), this.getMyNumber());
            System.out.println("Contact List");
            System.out.println(formated);

        }
    }
}

class Contact {
    private String name;
    private Set<String> emails = new HashSet<>();
    private Set<String> phones = new HashSet<>();

    public Contact(String name){
        this(name, null);
    }
    public Contact(String name, String email) {
        this(name, email, 0);
    }
    public Contact(String name, long phone) {
        this(name, null, phone);
    }
    public Contact(String name, String email, long phone) {
        this.name = name;
        if (email != null) {
            emails.add(email);
        }
        if (phone > 0) {
            String p = String.valueOf(phone);
            p = "(%s) %s-%s".formatted(p.substring(0,3), p.substring(3,6), p.substring(6));
            phones.add(p);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "%s: %s %s".formatted(name, emails, phones);
    }
    public Contact mergeContactData(Contact contact) {
        Contact newContact = new Contact(name);
        newContact.emails = new HashSet<>(this.emails);
        newContact.phones = new HashSet<>(this.phones);
        newContact.emails.addAll(contact.emails);
        newContact.phones.addAll(contact.phones);
        return newContact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return getName().equals(contact.getName());
    }

    @Override
    public int hashCode() {
        return 33 * getName().hashCode();
    }

    public void addEmail(String companyName) {
        String[] names = name.split(" ");
        String email = "%c%s@%s.com".formatted(name.charAt(0), names[names.length-1],
                companyName.replaceAll(" ", "").toLowerCase());
        if (!emails.add(email)) {
            System.out.println(name + " already has " + email);
        } else {
            System.out.println(name + " now has " + email);
        }
    }

    public void replaceEmailIfExists(String oldEmail, String newEmail) {
        if (emails.contains(oldEmail)) {
            emails.remove(oldEmail);
            emails.add(newEmail);
        }
    }
}

class ContactData {
    public static Scanner sc = new Scanner(System.in);
    public static final String phoneData = """
                Nagesh Ganji, 9548977654
                Micke Mous, 5658435455
                Sub Deved, 5698745862
                Rob Buffer, 5862458975
                Dave Hogan, 5658435455
                """;
    public static final String emailData = """
                Micke Mous, mmous@abc.com
                Nagesh Ganji, srina@disney.com
                Dave Hogan,   dhogan@gmail.com
                Robc Buffer, rbuffer@apple.com
                Dave Hogan, dhogan@gmail.com
                """;

    public static List<Contact> getData(String type) {
        List<Contact> dataList = new ArrayList<>();
        Scanner scanner = new Scanner(type.equalsIgnoreCase("phone") ?  phoneData : emailData);
        while (scanner.hasNext()){
            String[] data = scanner.nextLine().split(",");
            Arrays.asList(data).replaceAll(String::trim);
            if (type.equalsIgnoreCase("phone")) {
                dataList.add(new Contact(data[0], Long.parseLong(data[1])));
            } else {
                dataList.add(new Contact(data[0], data[1]));
            }
        }
        return dataList;
    }

    public static void readScannerData(int n) {
        //int[] myArr = new int[n];
        ArrayList<String> groceries = new ArrayList<>();
        ArrayList<Integer> values = new ArrayList<>(n);
        String printActions = """
                Available Actions:
                0 - to Shutdown
                1 - to add item to groceries list
                2 - to add items to int list
                3 - to delete item from the list""";
        boolean flag = true;

        while (true) {
            System.out.println(printActions);
            switch (Integer.parseInt(sc.nextLine())) {
                case 1 -> addItems(groceries);
                case 2 -> readInts(values);
                case 0 -> flag = false;
                default -> flag = false;
            }
        }
    }

    public static void creatematrix(int n, int m) {


    }
    public static void readInts(List<Integer> values) {
        System.out.printf("Add Ints seperated by Comma: " + values.size());
        String[] items = sc.nextLine().split(",");
        //ArrayList<Integer> it = new ArrayList<>(values.length);
        for (String s : items) {
            //s = s.trim();
            values.add(Integer.parseInt(s.trim()));
        }
        values.sort(Comparator.naturalOrder());
        values.forEach(System.out::print);
        //Arrays.stream(it).sorted().forEach(System.out::println);
    }
    public static void addItems(ArrayList<String> groceries) {
        System.out.printf("Add items seperated by Comma:");
        String[] items =  sc.nextLine().split(",");

        groceries.addAll(List.of(items));
        for (String i:items) {
            String trimmed = i.trim();
            if (groceries.indexOf(trimmed) < 0) {
                groceries.add(trimmed);
            }
        }
        groceries.sort(Comparator.naturalOrder());
        System.out.println("groceries = " + groceries);
    }
}

/*
while (true) {
        for (int i=0; i<n; i++){
        myArr[i] = sc.nextInt();
        }
        }*/

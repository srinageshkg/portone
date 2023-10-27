package com.dcp.portone.leet.stream;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamBasics {
    static final String str = """
        https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Collectors.html#method-summary
        import static java.util.stream.Collectors.*
        Supplier , Predicate, Consumer, Function, unary
        map filter skip boxed dropWhile takeWhile limit distinct sorted  forEach
        iterator reduct
        
        Terminal Operations ---
        Processing: forEach, forEachOrdered
        Statistical (Numeric) Reductions: average count max min sum summaryStatistics
        Transformations and Type reductions: collect reduce toArray toList
        Matching and Searching: allMatch, anyMatch, findAny, findFirst, noneMatch - Targets for Predicate Lambda exps
        
        Terminal Operations: R Collect, reduce, toArray, toList
        
        Optional Type: to addredd nullpointer exception  in cases of method return type
        
        Optional is another generic class  - don't construct instead use one of the factory mathods 
        empty, of ofNullable   - it is not Serializable
        
        
        """;

    public static int id = 1;

    public static void main(String[] args) {
//        stream1();
//        stream2();
//        stream3();
//        stream4();
//        reduction();
//        course();
//        mainCollect();
//        mainChallenge();
//        optionalType();
//        mainMapping();
        mainFinalChallange();
    }

    public static void mainFinalChallange() {

        Course pymc = new Course("PYMC", "Python Masterclass", 50);
        Course jmc = new Course("JMC", "Java Masterclass", 100);
        Course jgames = new Course("JGAME", "Creating games in Java");

        int currentYear = LocalDate.now().getYear();

        List<Student> students = Stream
                .generate(()->Student.getRandomStudent(pymc,jmc,jgames))
                .filter(s->s.getYearsSinceEnrolled() >= currentYear -4)
                .limit(2)
                .toList();

        System.out.println(students
                .stream()
                .mapToInt(Student::getYearEnrolled)
                .summaryStatistics());

    }
    public static void mainMapping() {
        Course pymc = new Course("PYMC", "Python Masterclass", 50);
        Course jmc = new Course("JMC", "Java Masterclass", 100);
        Course jgames = new Course("JGAME", "Creating games in Java");

//        List<Student> students = Stream.generate(() -> Student.getRandomStudent(pymc, jmc))
//                .limit(5000)
//                .toList();
        List<Student> students = IntStream
                .rangeClosed(1,5000)
                .mapToObj(s->Student.getRandomStudent(jmc, pymc))
                .toList();

        var mappedStudents = students.stream()
                .collect(Collectors.groupingBy(Student::getCountryCode));

        mappedStudents.forEach((k,v)-> System.out.println(k + " " + v.size()));

        System.out.println("--".repeat(30));
        int minAge = 25;
        var youngerSet = students.stream()
                .collect(Collectors.groupingBy(Student::getCountryCode,
                        Collectors.filtering(s->s.getAge() < minAge, Collectors.toList())));
        youngerSet.forEach((k,v)-> System.out.println(k + " " + v.size()));

        var experienced = students.stream()
                .collect(Collectors.partitioningBy(Student::hasProgExp));

        System.out.println("Exp Stude: " + experienced.get(true).size());

        var expCount = students.stream()
                .collect(Collectors.partitioningBy(Student::hasProgExp, Collectors.counting()));

        System.out.println("Exp Stude Count: " + expCount.get(false));

        var expCountActive = students.stream()
                .collect(Collectors.partitioningBy(
                        s->s.hasProgExp() && s.getMonthsSinceActive() == 0, Collectors.counting()));

        System.out.println("Exp and Act Stude Count: " + expCountActive.get(true));

        var multiLevel = students.stream()
                .collect(Collectors.groupingBy(Student::getCountryCode,
                        Collectors.groupingBy(Student::getGender)));
        multiLevel.forEach((k,v) -> {
            System.out.println(k);
            v.forEach((k1,v1)-> System.out.println("\t" + k1 + " " + v1.size()));
        });

        long studentBodyCount = 0;
        for (var list:experienced.values()) {
            studentBodyCount += list.stream().count();   // list.size()
        }
        System.out.println("studentBodyCount = " + studentBodyCount);

        studentBodyCount = experienced.values().stream()
                .map(l-> l.stream()
                        .filter(s-> s.getMonthsSinceActive() <= 3)
                        .count())
                .mapToLong(l-> l)
                .sum();
        System.out.println("studentBodyCount same as below = " + studentBodyCount);

        long count = experienced.values().stream()
                .flatMap(l->l.stream())
                .filter(l->l.getMonthsSinceActive() <= 3)
                .count();

        System.out.println("Active Students in the last 3 months = " + count);

        count = multiLevel.values().stream()
                .flatMap(map -> map.values().stream()
                                .flatMap(l->l.stream())
                        )
                .filter(l->l.getMonthsSinceActive() <= 3)
                .count();

        System.out.println("Multilevel Active Students in the last 3 months = " + count);

        count = multiLevel.values().stream()
                .flatMap(map -> map.values().stream())
                .flatMap(l->l.stream())
                .filter(l->l.getMonthsSinceActive() <= 3)
                .count();

        System.out.println("Multilevel Active Students in the last 3 months = " + count);
    }
    public static void optionalType() {
        Course pymc = new Course("PYMC", "Python Masterclass", 50);
        Course jmc = new Course("JMC", "Java Masterclass", 100);
        Course javagames = new Course("JGAME", "Creating games in Java");

        List<Student> students = Stream.generate(() -> Student.getRandomStudent(pymc, jmc))
                .limit(100)
                .collect(Collectors.toList());

        int minAge = 21;
        students.stream()
                .filter(s-> s.getAge() < minAge)
                .sorted(Comparator.comparing(Student::getAge))
                .findFirst()      // findAny()  .filter(s -> s.getAge() < minAge).min(Comparator.comparing(Student::getAge))
                .ifPresentOrElse(s->System.out.printf("Studnet %d from %s is %d%n",
                                s.getStudentId(), s.getCountryCode(), s.getAge()),
                        () -> System.out.println("Din't find any under "+ minAge));

        int avgAge = 21;
        students.stream()
                .filter(s->s.getAge() <= avgAge)
                .mapToInt(s->s.getAge())
                .average()
                .ifPresentOrElse(a -> System.out.printf("Studnet Avg age under %d is %.2f%n",avgAge,a),
                        () -> System.out.println("Din't find any under "+ avgAge));

        students.stream()
                .filter(s->s.getAge() < minAge)
                .map(s->s.getCountryCode())
                .distinct()
                .reduce((a,b)-> String.join(", ", a, b))
                .ifPresentOrElse(System.out::println, ()-> System.out.println("Nothing found"));

        students.stream()
                .map(s->s.getCountryCode())
                .distinct()
                .map(l-> String.join(", ", l))
                .filter(l-> l.contains("FR"))
                .findAny()
                .ifPresentOrElse(System.out::println, ()-> System.out.println("Missing FR " + LocalDate.now()));

        //----------------------------------------------------------------------------
        Optional<Student> o1 = getStudent(new ArrayList<>(), "first");
        System.out.println("Empty: " + o1.isEmpty() + " Present: " + o1.isPresent());
        System.out.println("o1 = " + o1);
        o1.ifPresentOrElse(System.out::println, ()->System.out.println("---> Empty"));

        Optional<Student> o2 = getStudent(students, "first");
        System.out.println("Empty: " + o2.isEmpty() + " Present: " + o2.isPresent());
        System.out.println("o2 = " + o2);
        if (o2.isPresent()) {
            System.out.println("o2 = " + o2.get());
        }
        o2.ifPresent(System.out::println);

//        Student firstStu = o2.orElse(getDummyStudent(jmc));
        Student firstStu = o2.orElseGet(() -> getDummyStudent(jmc));
        long id = firstStu.getStudentId();

        List<String> countries = students.stream()
                .map(s -> s.getCountryCode())
                .distinct()
                .sorted()
                .toList();

        Optional.of(countries)
                .map(l -> String.join(", ",l))
                .filter(l -> l.contains("US"))
                .ifPresentOrElse(System.out::println, () -> System.out.println("Missing US"));


    }

    private static Student getDummyStudent(Course... courses) {
        System.out.println("Getting dummy Student");
        return new Student("NO", 1, 1, "U", false, courses);
    }
    private static Optional<Student> getStudent(List<Student> list, String type) {
        if (list == null || list.size() == 0) {
            return Optional.empty();
        } else if (type.equals("first")) {
            return Optional.ofNullable(list.get(0));
        } else if (type.equals("last")){
            return Optional.ofNullable(list.get(list.size()-1));
        }
        return Optional.ofNullable(list.get(new Random().nextInt(list.size())));
    }
    public static void mainChallenge() {
        Course pymc = new Course("PYMC", "Python Masterclass", 50);
        Course jmc = new Course("JMC", "Java Masterclass", 100);
        Course javagames = new Course("JGAME", "Creating games in Java");

        // Both works
        //List<Student> mcStudents = Stream.generate((s)->Student.getRandomStudent(pymc, jmc, javagames))
        //        .limit(5000)
        //        .collect(Collectors.toList());

        List<Student> rcStudents = IntStream
                .rangeClosed(1,5000)
                .mapToObj(s -> Student.getRandomStudent(pymc, jmc))
                .toList();

        //List<Student> students = Stream
        //        .iterate(1, s-> s <= 5000, s -> s + 1)
        //        .map(s -> Student.getRandomStudent(jmc, pymc))
        //        .toList();

        var totalPerComplete = rcStudents.stream()
                .mapToDouble(s -> s.getPercentageComplete("JMC"))
                .reduce(0, Double::sum);

        double avePercent = totalPerComplete / rcStudents.size();
        System.out.printf("Avg %% Complete : %.2f%% %n", avePercent);

        int topPercent = (int) (1.25 * avePercent);

        List<Student> hardWorkers = rcStudents.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentageComplete("JMC") >= topPercent)
                .toList();

        System.out.println("hardWorkers = " + hardWorkers.size());

        Comparator<Student> longTermStudent = Comparator.comparing(Student::getYearEnrolled);

        List<Student> jghardworkers = rcStudents.stream()
                .filter(s->s.getPercentageComplete("JMC") >= topPercent)
                .filter(s->s.getMonthsSinceActive("JMC") == 0)
                .sorted(longTermStudent)
                .limit(10)
                .toList();

        jghardworkers.forEach(s -> {
            s.addCourse(javagames);
//            System.out.println("s = " + s);
            System.out.print(s.getStudentId() + " ");
        });
        System.out.println();

        rcStudents.stream()
                .filter(s->s.getPercentageComplete("JMC") >= topPercent)
                .filter(s->s.getMonthsSinceActive("JMC") == 0)
                .sorted(longTermStudent)
                .limit(10)
                .toList()
                .forEach(s -> {
                    s.addCourse(javagames);
                    System.out.print(s.getStudentId() + " ");
                });
    }
    public static void mainCollect() {
        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");

        List<Student> students = Stream.generate(() -> Student.getRandomStudent(pymc, jmc))
                .limit(1000)
                .toList();

        Set<Student> usStudents = students.stream().filter(s -> s.getCountryCode().equals("US"))
                .collect(Collectors.toSet());
        System.out.println("usStudents = " + usStudents.size());

        Set<Student> underThirty = students.stream()
                .filter(s-> s.getAge() < 30)
                .collect(Collectors.toSet());

        Set<Student> youngAmericans1 = new TreeSet<>(Comparator.comparing(Student::getStudentId));
        youngAmericans1.addAll(usStudents);
        youngAmericans1.retainAll(underThirty);
        youngAmericans1.forEach(s->System.out.print(s.getStudentId() + ", "));
        System.out.println();

        Set<Student> youngAme2 = students.stream()
                .filter(s -> s.getCountryCode().equals("US"))
                .filter(s-> s.getAge() < 30)
                .collect(() -> new TreeSet<>(Comparator.comparing(Student::getStudentId)), TreeSet::add, TreeSet::addAll);
        youngAme2.forEach(s -> System.out.print(s.getStudentId() + ", "));
        System.out.println();

        String countryList = students.stream()
                .map(Student::getCountryCode)
                .distinct()
                .sorted()
                .reduce("", (r,v) -> r + " " + v);
        System.out.println("countryList = " + countryList);
    }
    public static void course() {
        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");
/*        Student tim = new Student("US", 2019, 26, "M", true, jmc, pymc);
        System.out.println("tim = " + tim);

        tim.watchLecture("JMC", 10, 5, 2019);
        tim.watchLecture("PYMC", 7, 7, 2020);

        System.out.println("tim = " + tim);*/

        Student[] students = new Student[1000];
        Arrays.setAll(students, (i) -> Student.getRandomStudent(pymc, jmc));
//        var studentsStream = Arrays.stream(students);
//        Stream<Student> students = Stream.generate(()->Student.getRandomStudent(jmc, pymc))
//                .limit(1000);
//                .forEach(System.out::println);
//        System.out.println("Male Studnets: " + studentsStream.filter(s -> s.getGender().equals("M")).count());

        for (String gender: List.of("M", "F", "U")) {
            var gStudents = Arrays.stream(students).filter(s->s.getGender().equals(gender));
            System.out.println("#of " + gender + " students: " + gStudents.count());
        }

        List<Predicate<Student>> listPerAges = List.of(
                (s) -> s.getAge() < 30,
                (s) -> s.getAge() >= 30 && s.getAge() < 60
        );
        long total = 0;
        for (int i=0; i<listPerAges.size(); i++) {
            var aStudents = Arrays.stream(students)
                    .filter(listPerAges.get(i));
            long cnt = aStudents.count();
            total += cnt;

            System.out.printf("# of students (%s) = %d%n",
                    i == 0 ? " <30 " : ">=30 & <60", cnt);
        }
        System.out.printf("# of students  >= 60 = (%d)%n", students.length - total);

        var ageStream = Arrays.stream(students)
                .mapToInt(Student::getAgeEnrolled);

        System.out.println("ageStream = " + ageStream.summaryStatistics());

        var currentAgeStream = Arrays.stream(students)
                .mapToInt(Student::getAge);
        System.out.println("curretnAgeStream = " + currentAgeStream.summaryStatistics());

        Arrays.stream(students)
                .map(Student::getCountryCode)
                .distinct()
                .sorted()
                .forEach(s->System.out.print(s + " "));
        System.out.println();

        boolean longTerm = Arrays.stream(students)
                .anyMatch(s -> (s.getAge()-s.getAgeEnrolled() >= 7) && (s.getMonthsSinceActive() < 12));
        System.out.println("longTermActive Students = " + longTerm);

        long longTermCnt = Arrays.stream(students)
                .filter(s -> (s.getAge()-s.getAgeEnrolled() >= 7) && (s.getMonthsSinceActive() < 12))
                .count();
        System.out.println("longTermCnt = " + longTermCnt);

        List<Student> longTermStudents = Arrays.stream(students)
                .filter(s -> (s.getAge()-s.getAgeEnrolled() >= 7) && (s.getMonthsSinceActive() < 12))
                .filter(s -> !s.hasProgExp())
                .limit(5)
                .toList();
//                .forEach(System.out::println);

        var longTermLearners = Arrays.stream(students)
                .filter(s -> (s.getAge()-s.getAgeEnrolled() >= 7) && (s.getMonthsSinceActive() < 12))
                .filter(s -> !s.hasProgExp())
                .limit(5)
                .toArray(Student[]::new);
//                .toArray(size -> new Student[size]);

        var learners = Arrays.stream(students)
                .filter(s -> (s.getAge()-s.getAgeEnrolled() >= 7) && (s.getMonthsSinceActive() < 12))
                .filter(s -> !s.hasProgExp())
                .limit(5)
                .collect(Collectors.toList());

        Collections.shuffle(learners);
//
    }

    public static void reduction() {
        var result = IntStream.iterate(0, i->i<=1000, i-> i = i+3)
                .summaryStatistics();
        System.out.println("result = " + result);

        var leapYrData = IntStream.iterate(2000, i-> i<2025, i->i = i+1)
                .filter(i-> i % 4 == 0)
                .peek(i->System.out.printf("%d ",i))
                .summaryStatistics();
        System.out.println("leapYrData = " + leapYrData);

        Seat[] seats = new Seat[100];
        Arrays.setAll(seats, i-> new Seat((char)('A' + i/10), (i%10)+1));
//        Arrays.asList(seats).forEach(System.out::println);
//        System.out.println(9 / 10); // 9/10 == 0  %% 9%10 == 9
        var reservationCount = Arrays.stream(seats)
                .filter(Seat::isReserved)
                .count();
        System.out.println("reservationCount = " + reservationCount);
        var hasBookings = Arrays.stream(seats)
                .anyMatch(Seat::isReserved);
        System.out.println("hasBookings = " + hasBookings);
        
        var fullyBooked = Arrays.stream(seats)
                .allMatch(Seat::isReserved);
        System.out.println("fullyBooked = " + fullyBooked);
        
        var eventWashedOut = Arrays.stream(seats)
                .noneMatch(Seat::isReserved);
        System.out.println("eventWashedOut = " + eventWashedOut);
                
    }
    public static void stream4() {
        int maxSeats = 100;
        int seatsInRow = 10;
        var stream = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> new SeatP((char) ('A' + (i / seatsInRow)), (i % seatsInRow) + 1))
                .sorted(Comparator.comparing(SeatP::price)
                        .thenComparing(SeatP::toString));
//                .mapToDouble(Seat::price)
//                .boxed()
//                .map("%.2f"::formatted);
//                .mapToObj("%.2f"::formatted);

        stream.forEach(System.out::println);
    }

    public static void stream3() {
        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
//                .dropWhile(c -> c <= 'D')
//                .takeWhile(c -> c <= 'v')
//                .skip(10)
                .peek(i -> System.out.printf("Watching %d", i))
                .filter(i -> Character.toUpperCase(i) < 'G' || Character.toUpperCase(i) > 'R')
                .forEach(d -> System.out.printf("%c ", d));
    }

    public static void stream2() {
        int seed = 1;
        var streamB = Stream.iterate(seed, i -> i <= 15, i -> i + 1)
                .map(i -> "B" + i);

        seed += 15;
        var streamI = Stream.iterate(seed, i -> i + 1)
                .limit(15)
                .map(i -> "I" + i);

        seed += 15;
        int nSeed = seed;
        String[] oLables = new String[15];
        Arrays.setAll(oLables, i -> "N" + (nSeed + i));
        var streamN = Arrays.stream(oLables);

        seed += 15;
        var streamG = Stream.of("G46", "G47");

        seed += 15;
        int rSeed = seed;
        var streamO = Stream.generate(StreamBasics::getNumber)
                .limit(15)
                .map(s -> "O" + (s + rSeed));

        var streamBI = Stream.concat(streamB, streamI);
        var streamNG = Stream.concat(streamN, streamG);
        var streamBING = Stream.concat(streamBI, streamNG);
        var streamBINGO = Stream.concat(streamBING, streamO);

        Stream.generate(() -> new Random().nextInt(rSeed, rSeed + 15))
                .distinct()
                .limit(15)
                .map(i -> "O" + i)
                .sorted()
                .forEach(System.out::println);
//        streamO.forEach(System.out::println);
    }

    public static void stream1() {
        List<String> bingoPool = new ArrayList<>(75);
        int start = 1;

        for (char c : "BINGO".toCharArray()) {
            for (int i = start; i < (start + 15); i++) {
                bingoPool.add("" + c + i);
//                System.out.println("" + c + i);
            }
            start += 15;
        }
        Collections.shuffle(bingoPool);
        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }
        System.out.println("--".repeat(30));

//        List<String> firstOnes = bingoPool.subList(0,15);
        List<String> firstOnes = new ArrayList<>(bingoPool.subList(0, 15));
        firstOnes.sort(Comparator.naturalOrder());
        firstOnes.replaceAll(s -> {
            if (s.indexOf("G") == 0 || s.indexOf("O") == 0) {
                String updated = s.charAt(0) + "-" + s.substring(1);
                System.out.print(updated + " ");
                return updated;
            }
            return s;
        });
        System.out.println();
        System.out.println("--".repeat(30));
        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }
        System.out.println("--".repeat(30));

        var tempStream = bingoPool.stream()
                .limit(15)
                .filter(s -> s.indexOf("G") == 0 || s.indexOf("O") == 0)
                .map(s -> s.charAt(0) + "-" + s.substring(1))
                .sorted();
//                .forEach(s -> System.out.print(s + " "));
        tempStream.forEach(s -> System.out.print(s + " "));
        System.out.println();
        System.out.println("--".repeat(30));

        //-------------------------------------------------------------------------------

        String[] strings = {"One", "Two", "Three"};
        var firstStream = Arrays.stream(strings)
                .sorted(Comparator.reverseOrder());
//                .forEach(System.out::println);

        var secondStream = Stream.of("Six", "Five", "Four")
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder());
//                .forEach(System.out::println);

        Stream.concat(secondStream, firstStream)
                .map(c -> c.charAt(0) + " " + c)
                .map(c -> c + " ")
                .forEach(System.out::print);

        System.out.println();

        Map<Character, int[]> myMap = new LinkedHashMap<>();
        int bingoIndex = 1;
        for (char c : "BINGO".toCharArray()) {
            int[] numbers = new int[15];
            int labelNo = bingoIndex;
            Arrays.setAll(numbers, i -> i + labelNo);
            myMap.put(c, numbers);
            bingoIndex += 15;
        }
        System.out.println("--".repeat(30));
        int[] testInt = new int[10];
        Arrays.setAll(testInt, i -> i + 1);
        Arrays.stream(testInt).forEach(System.out::print);
        System.out.println("   End of Arrays.setAll");
        System.out.println("--".repeat(30));
        // ------------------------------------------------
        myMap.entrySet()
                .stream()
                .map(e -> e.getKey() + " has range: " + e.getValue()[0] + " - " + e.getValue()[e.getValue().length - 1])
                .forEach(e -> System.out.print(e + " "));
        System.out.println();
        Random random = new Random();
        Stream.generate(() -> random.nextInt(2))
                .limit(10)
                .forEach(s -> System.out.print(s + " "));

        System.out.println("--".repeat(30));

        IntStream.iterate(1, i -> i + 1)
                .filter(StreamBasics::isPrime)
                .limit(20)
                .forEach(i -> System.out.print(i + ", "));
        System.out.println();

        IntStream.iterate(1, n -> n < 100, i -> i + 1)
                .filter(StreamBasics::isPrime)
//                .limit(20)
                .forEach(i -> System.out.print(i + ", "));
        System.out.println();

        IntStream.range(1, 97)
                .filter(StreamBasics::isPrime)
                .forEach(i -> System.out.print(i + ", "));
        System.out.println();

        IntStream.rangeClosed(1, 97)
                .filter(StreamBasics::isPrime)
                .forEach(i -> System.out.print(i + ", "));
        System.out.println();

    }

    public static boolean isPrime(int wholeNumber) {
        if (wholeNumber <= 2) {
            return wholeNumber == 2;
        }
        for (int divisor = 2; divisor < wholeNumber; divisor++) {
            if (wholeNumber % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    public static int getNumber() {
        return id++;
    }

    public record Seat(char rowMarker, int seatNumber, boolean isReserved) {

        public Seat(char rowMarker, int seatNumber) {
            this(rowMarker, seatNumber, new Random().nextBoolean());
        }

        @Override
        public String toString() {
            return "%c%3d %s".formatted(rowMarker, seatNumber, isReserved);
        }
    }
    public record SeatP(char rowMarker, int seatNumber, double price) {
        public SeatP(char rowMarker, int seatNumber) {
            this(rowMarker, seatNumber,
                    rowMarker > 'C' && seatNumber <= 2 || seatNumber >= 9 ? 50 : 75);
        }

        @Override
        public String toString() {
            return "%c%3d %.0f".formatted(rowMarker, seatNumber, price);
        }
    }
}

class Student {
//(long studentId, String countryCode, int yearEnrolled, int ageEnrolled, String gender, boolean progExp)
    private static long lastStudentId = 1;
    private final static Random random = new Random();
    private final long studentId;
    private final String countryCode;
    private final int yearEnrolled;
    private final int ageEnrolled;
    private final String gender;
    private final boolean progExp;
    private final Map<String, CourseEngagement> engagementMap = new HashMap<>();

    public void watchLecture(String courseCode, int lectureNumber, int month, int year) {
        var activity = engagementMap.get(courseCode);
        if (activity != null) {
            activity.watchLecture(lectureNumber, LocalDate.of(year, month, 1));
        }
    }

    public void addCourse(Course newCourse) {
        addCourse(newCourse, LocalDate.now());
    }
    public void addCourse(Course newCourse, LocalDate enrollDate) {
        engagementMap.put(newCourse.courseCode(),
                new CourseEngagement(newCourse, enrollDate, "Enrollment"));
    }

    public int getAge() {
        return ageEnrolled + getYearsSinceEnrolled();
    }
    public int getMonthsSinceActive(String courseCode) {
        CourseEngagement info = engagementMap.get(courseCode);
        return info == null ? 0: info.getMonthsSinceActive();
    }

    public int getMonthsSinceActive() {
        int inactiveMonths = (LocalDate.now().getYear() - 2014) * 12;
        for (String key: engagementMap.keySet()) {
            inactiveMonths = Math.min(inactiveMonths, getMonthsSinceActive(key));
        }
        return inactiveMonths;
    }
    public double getPercentageComplete(String courseCode) {
        var info = engagementMap.get(courseCode);
        return (info == null) ? 0 : info.getPercentageComplete();
    }

    public int getYearsSinceEnrolled() {
        return LocalDate.now().getYear() - yearEnrolled;
    }

    private static String getRandomVal(String... data) {
        return data[random.nextInt(data.length)];
    }

    private static Course[] getRandomSelection(Course... courses) {
        int courseCount = random.nextInt(1, courses.length);
        List<Course> courseList = new ArrayList<>(Arrays.asList(courses));
        Collections.shuffle(courseList);
        List<Course> selectedCourses = courseList.subList(0,courseCount);
        return selectedCourses.toArray(new Course[0]);
    }
    public static Student getRandomStudent(Course... courses) {
        int maxYear = LocalDate.now().getYear() + 1;

        Course[] randomCourses = getRandomSelection(courses);

        Student student = new Student(
                getRandomVal("AU", "US", "CN", "IN", "GB", "UA"),
                random.nextInt(2015, maxYear),
                random.nextInt(18,90),
                getRandomVal("M", "F", "U"),
                random.nextBoolean(),
                randomCourses );
//                courses );

//        for(Course course: courses) {
        for(Course course: randomCourses) {
            int lecture = random.nextInt(30, course.lectureCount());
            int year = random.nextInt(student.getYearEnrolled(), maxYear);
            int month = random.nextInt(1,13);
            if (year == (maxYear-1)) {
                if (month > LocalDate.now().getMonthValue()) {
                    month = LocalDate.now().getMonthValue();
                }
            }
            student.watchLecture(course.courseCode(), lecture, month, year);
        }
        return student;

    }

    public long getStudentId() {
        return studentId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getYearEnrolled() {
        return yearEnrolled;
    }

    public int getAgeEnrolled() {
        return ageEnrolled;
    }

    public String getGender() {
        return gender;
    }

    public boolean hasProgExp() {
        return progExp;
    }

    public Map<String, CourseEngagement> getEngagementMap() {
        return Map.copyOf(engagementMap);
    }
    Student(String countryCode, int yearEnrolled, int ageEnrolled, String gender,
            boolean progExp, Course... courses) {
        this.studentId = lastStudentId++;
        this.countryCode = countryCode;
        this.yearEnrolled = yearEnrolled;
        this.ageEnrolled = ageEnrolled;
        this.gender = gender;
        this.progExp = progExp;

        for (Course course: courses) {
            addCourse(course, LocalDate.of(yearEnrolled, 1, 1));
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", countryCode='" + countryCode + '\'' +
                ", yearEnrolled=" + yearEnrolled +
                ", ageEnrolled=" + ageEnrolled +
                ", gender='" + gender + '\'' +
                ", progExp=" + progExp +
                ", engagementMap=" + engagementMap +
                '}';
    }
}
record Course(String courseCode, String title, int lectureCount) {
    @Override
    public String courseCode() {
        return courseCode;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public int lectureCount() {
        return lectureCount;
    }
    public Course {
        if (lectureCount <= 0)
            lectureCount = 1;
    }

    public Course(String courseCode, String title) {
        this(courseCode, title, 40);
    }

    @Override
    public String toString() {
        return "%s %s".formatted(courseCode, title);
    }
}

class CourseEngagement {
    private final Course course;
    private final LocalDate enrollmentDate;
    private String engagementType;
    private int lastLecture;
    private LocalDate lastActivityDate;

    public CourseEngagement(Course course, LocalDate enrollmentDate, String engagementType) {
        this.course = course;
        this.enrollmentDate = this.lastActivityDate = enrollmentDate;
        this.engagementType = engagementType;
    }

    public String getCourseCode() {
        return course.courseCode();
    }
    public int getEnrolementYear() {
        return enrollmentDate.getYear();
    }
    public int getLastActivtyYear() {
        return lastActivityDate.getYear();
    }
    public String getLastActivtyMonth() {
//        return lastActivityDate.getMonth().toString();
        return "%tb".formatted(lastActivityDate);
    }
    public int getMonthsSinceActive() {
        LocalDate now = LocalDate.now();
        var months = Period.between(lastActivityDate, now).toTotalMonths();
        return (int) months;
    }

    public double getPercentageComplete() {
        return lastLecture * 100.0 / course.lectureCount();
    }
    public void watchLecture(int lectureNumber, LocalDate currentDate) {
        lastLecture = Math.max(lectureNumber, lastLecture);
        lastActivityDate = currentDate;
        engagementType = "Lecture " + lastLecture;
    }

    @Override
    public String toString() {
        return "%s: %s %d %s [%d]".formatted(course.courseCode(), getLastActivtyMonth(), getLastActivtyYear(),
                engagementType, getMonthsSinceActive());
    }
}

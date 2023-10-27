package com.dcp.portone.service;

import com.dcp.portone.entity.Product;
import com.dcp.portone.service.Studentiit;
import com.jayway.jsonpath.internal.function.numeric.Sum;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static java.util.stream.DoubleStream.iterate;

public class ThreePrizes {
    Studentiit s1 = Studentiit.builder()
            .cardNo(0)
            .name("Bhuv")
            .dateOfBirth("11/07/1997")
            .city("Erode")
            .gender('M')
            .marksiit(Marksiit.builder()
                    .maths(68)
                    .physics(64)
                    .chemistry(78)
                    .total(210)
                    .build())
            .build();

    Studentiit s2 = Studentiit.builder()
            .cardNo(1)
            .name("Harish")
            .dateOfBirth("06/03/1997")
            .city("Salem")
            .gender('M')
            .marksiit(Marksiit.builder()
                    .maths(62)
                    .physics(45)
                    .chemistry(91)
                    .total(198)
                    .build())
            .build();

    Studentiit s3 = Studentiit.builder()
            .cardNo(2)
            .name("Shasa")
            .dateOfBirth("01/04/1997")
            .city("Chennai")
            .gender('M')
            .marksiit(Marksiit.builder()
                    .maths(57)
                    .physics(54)
                    .chemistry(77)
                    .total(188)
                    .build())
            .build();

    Studentiit s4 = Studentiit.builder()
            .cardNo(3)
            .name("Rida")
            .dateOfBirth("05/05/1997")
            .city("Chennai")
            .gender('F')
            .marksiit(Marksiit.builder()
                    .maths(42)
                    .physics(53)
                    .chemistry(78)
                    .total(173)
                    .build())
            .build();

    Studentiit s5 = Studentiit.builder()
            .cardNo(4)
            .name("Ritika")
            .dateOfBirth("11/17/1997")
            .city("Madhurai")
            .gender('F')
            .marksiit(Marksiit.builder()
                    .maths(87)
                    .physics(64)
                    .chemistry(89)
                    .total(240)
                    .build())
            .build();

    Studentiit s6 = Studentiit.builder()
            .cardNo(5)
            .name("Akshaya")
            .dateOfBirth("02/08/1997")
            .city("Chennai")
            .gender('F')
            .marksiit(Marksiit.builder()
                    .maths(71)
                    .physics(92)
                    .chemistry(84)
                    .total(247)
                    .build())
            .build();

    Studentiit s7 = Studentiit.builder()
            .cardNo(6)
            .name("Sameer")
            .dateOfBirth("03/23/1997")
            .city("Erode")   // Ambur
            .gender('M')
            .marksiit(Marksiit.builder()
                    .maths(81)
                    .physics(82)
                    .chemistry(87)
                    .total(250)
                    .build())
            .build();

    Studentiit s8 = Studentiit.builder()
            .cardNo(7)
            .name("Adithi")
            .dateOfBirth("03/15/1997")
            .city("Chennai")   // Vellore
            .gender('F') //M
            .marksiit(Marksiit.builder()
                    .maths(84)
                    .physics(92)
                    .chemistry(76)
                    .total(252)
                    .build())
            .build();

    Studentiit s9 = Studentiit.builder()
            .cardNo(8)
            .name("Surya")
            .dateOfBirth("02/28/1997")
            .city("Bengaluru")
            .gender('M')
            .marksiit(Marksiit.builder()
                    .maths(74)
                    .physics(64)
                    .chemistry(51)
                    .total(189)
                    .build())
            .build();

    Studentiit s10 = Studentiit.builder()
            .cardNo(9)
            .name("Clarance")
            .dateOfBirth("12/06/1997")
            .city("Bengaluru")
            .gender('M')
            .marksiit(Marksiit.builder()
                    .maths(63)
                    .physics(88)
                    .chemistry(73)
                    .total(224)
                    .build())
            .build();

    Studentiit s11 = Studentiit.builder()
            .cardNo(10)
            .name("Kavya")
            .dateOfBirth("01/12/1997")
            .city("Chennai")
            .gender('F')
            .marksiit(Marksiit.builder()
                    .maths(64)
                    .physics(72)
                    .chemistry(68)
                    .total(204)
                    .build())
            .build();

    Studentiit s12 = Studentiit.builder()
            .cardNo(11)
            .name("Rahul")
            .dateOfBirth("04/30/1997")
            .city("Bengaluru")
            .gender('M')
            .marksiit(Marksiit.builder()
                    .maths(97)
                    .physics(92)
                    .chemistry(92)
                    .total(281)
                    .build())
            .build();

    Studentiit s13 = Studentiit.builder()
            .cardNo(12)
            .name("Srinidhi")
            .dateOfBirth("01/14/1997")
            .city("Chennai")
            .gender('F')
            .marksiit(Marksiit.builder()
                    .maths(62)
                    .physics(45)
                    .chemistry(91)
                    .total(198)
                    .build())
            .build();

    Studentiit s14 = Studentiit.builder()
            .cardNo(13)
            .name("Gopi")
            .dateOfBirth("05/06/1997")
            .city("Madhurai")
            .gender('M')
            .marksiit(Marksiit.builder()
                    .maths(65)
                    .physics(73)
                    .chemistry(89)
                    .total(227)
                    .build())
            .build();

    Studentiit s15 = Studentiit.builder()
            .cardNo(14)
            .name("Sophia")
            .dateOfBirth("07/23/1997")
            .city("Trichy")
            .gender('F')
            .marksiit(Marksiit.builder()
                    .maths(89)
                    .physics(62)
                    .chemistry(93)
                    .total(244)
                    .build())
            .build();

    Studentiit s16 = Studentiit.builder()
            .cardNo(15)
            .name("Goutami")
            .dateOfBirth("09/22/1997")
            .city("Ambur")
            .gender('F')
            .marksiit(Marksiit.builder()
                    .maths(76)
                    .physics(58)
                    .chemistry(90)
                    .total(224)
                    .build())
            .build();

    Studentiit s17 = Studentiit.builder()
            .cardNo(16)
            .name("Tauseef")
            .dateOfBirth("12/30/1997")
            .city("Trichy")
            .gender('M')
            .marksiit(Marksiit.builder()
                    .maths(87)
                    .physics(86)
                    .chemistry(43)
                    .total(216)
                    .build())
            .build();

    Studentiit s18 = Studentiit.builder()
            .cardNo(17)
            .name("Arshad")
            .dateOfBirth("12/14/1997")
            .city("Chennai")
            .gender('M')
            .marksiit(Marksiit.builder()
                    .maths(62)
                    .physics(81)
                    .chemistry(67)
                    .total(210)
                    .build())
            .build();

    Studentiit s19 = Studentiit.builder()
            .cardNo(18)
            .name("Abirami")
            .dateOfBirth("10/09/1997")
            .city("Erode")
            .gender('F')
            .marksiit(Marksiit.builder()
                    .maths(72)
                    .physics(92)
                    .chemistry(97)
                    .total(261)
                    .build())
            .build();

    Studentiit s20 = Studentiit.builder()
            .cardNo(19)
            .name("Vetrivel")
            .dateOfBirth("08/30/1997")
            .city("Trichy")
            .gender('M')
            .marksiit(Marksiit.builder()
                    .maths(56)
                    .physics(78)
                    .chemistry(62)
                    .total(196)
                    .build())
            .build();

    Studentiit s21 = Studentiit.builder()
            .cardNo(20)
            .name("Kalyan")
            .dateOfBirth("09/17/1997")
            .city("Vellore")
            .gender('M')
            .marksiit(Marksiit.builder()
                    .maths(93)
                    .physics(68)
                    .chemistry(91)
                    .total(252)
                    .build())
            .build();

    Studentiit s22 = Studentiit.builder()
            .cardNo(21)
            .name("Monika")
            .dateOfBirth("03/15/1997")
            .city("Bengaluru")
            .gender('F')
            .marksiit(Marksiit.builder()
                    .maths(78)
                    .physics(69)
                    .chemistry(74)
                    .total(221)
                    .build())
            .build();

    Studentiit s23 = Studentiit.builder()
            .cardNo(22)
            .name("Priya")
            .dateOfBirth("07/17/1997")
            .city("Nagercoil")
            .gender('F')
            .marksiit(Marksiit.builder()
                    .maths(62)
                    .physics(62)
                    .chemistry(57)
                    .total(181)
                    .build())
            .build();

    Studentiit s24 = Studentiit.builder()
            .cardNo(23)
            .name("Deepika")
            .dateOfBirth("05/13/1997")
            .city("Bengaluru")
            .gender('F')
            .marksiit(Marksiit.builder()
                    .maths(97)
                    .physics(91)
                    .chemistry(88)
                    .total(276)
                    .build())
            .build();

    Studentiit s25 = Studentiit.builder()
            .cardNo(24)
            .name("Siddarth")
            .dateOfBirth("12/26/1997")
            .city("Madhurai")
            .gender('M')
            .marksiit(Marksiit.builder()
                    .maths(44)
                    .physics(72)
                    .chemistry(58)
                    .total(174)
                    .build())
            .build();

    Studentiit s26 = Studentiit.builder()
            .cardNo(25)
            .name("Geeta")
            .dateOfBirth("05/16/1997")
            .city("Chennai")
            .gender('F')
            .marksiit(Marksiit.builder()
                    .maths(87)
                    .physics(75)
                    .chemistry(92)
                    .total(254)
                    .build())
            .build();

    Studentiit s27 = Studentiit.builder()
            .cardNo(26)
            .name("Jk")
            .dateOfBirth("07/22/1997")
            .city("Chennai")
            .gender('M')
            .marksiit(Marksiit.builder()
                    .maths(74)
                    .physics(71)
                    .chemistry(82)
                    .total(227)
                    .build())
            .build();

    Studentiit s28 = Studentiit.builder()
            .cardNo(27)
            .name("Jagan")
            .dateOfBirth("03/04/1997")
            .city("Madhurai")
            .gender('M')
            .marksiit(Marksiit.builder()
                    .maths(81)
                    .physics(76)
                    .chemistry(52)
                    .total(209)
                    .build())
            .build();

    Studentiit s29 = Studentiit.builder()
            .cardNo(28)
            .name("Nisha")
            .dateOfBirth("09/10/1997")
            .city("Madhurai")
            .gender('F')
            .marksiit(Marksiit.builder()
                    .maths(74)
                    .physics(83)
                    .chemistry(83)
                    .total(240)
                    .build())
            .build();

    Studentiit s30 = Studentiit.builder()
            .cardNo(29)
            .name("Naveen")
            .dateOfBirth("10/13/1997")
            .city("Vellore")
            .gender('M')
            .marksiit(Marksiit.builder()
                    .maths(72)
                    .physics(66)
                    .chemistry(81)
                    .total(219)
                    .build())
            .build();

    public List<Studentiit> studentiitList = new ArrayList<Studentiit>(Arrays.asList(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26,s27,s28,s29,s30));

    //public List<Studentiit> studentiitList = new ArrayList<Studentiit>(Arrays.asList(s1,s2,s3,s4,s5,s6,s7,s8));
    //        ,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26,s27,s28,s29,s30));

    public List<Studentiit> getStudentiitList(){
        return studentiitList;
    }

    public void streamexamples(){

        Integer[] inra = new Integer[6];

        String[] strings = {"Madhurai", "Vellore", "Chennai", "Siddarth", "Jagan"};
        Arrays.fill(inra, 8);
        System.out.println("inra fill :" + Arrays.stream(inra).reduce(0,Integer::sum));

        enum Gfg { CODE, LEARN, CONTRIBUTE, QUIZ, MCQ };
        EnumSet<Gfg> set1, set2, set3, set4;
        set1 = EnumSet.of(Gfg.QUIZ, Gfg.LEARN, Gfg.CODE);
        set2 = EnumSet.complementOf(set1);
        set3 = EnumSet.allOf(Gfg.class);
        set4 = EnumSet.range(Gfg.CODE, Gfg.CONTRIBUTE);
        System.out.println("Set 1: " + set1);
        System.out.println("Set 2: " + set2);
        System.out.println("Set 3: " + set3);
        System.out.println("Set 4: " + set4);
        Iterator<Gfg> iterate = set3.iterator();

        while (iterate.hasNext()) {
            System.out.print(iterate.next());
            System.out.print(",&&&&  ");
        }


        boolean contains = Arrays.stream(strings).anyMatch("Chennai"::equals);
        System.out.println("Strams anyMatch contains ai in " + contains + " in " + strings);

        List<Integer> mylist = new ArrayList<>();
        mylist.addAll(List.of(2,15, 6,9,4,20));

        System.out.println("9 in " + mylist.toString() + mylist.stream().anyMatch(x -> x == 9));

        LocalDate ldtoday = LocalDate.now();
        LocalDate nxtWed = ldtoday.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
        System.out.println(ldtoday);
        System.out.println(nxtWed);

        System.out.println(mylist.stream().filter(i -> i % 2 == 0).reduce(0,Integer::sum));
        mylist.stream().map(i->i+5).collect(toList()).forEach(System.out::println);
        //Collections.sort(list, (s1,s2)-> s1.compareTo(s2));
        //mylist.stream().sorted((i1,i2)-> i1.compareTo(i2)).forEach(System.out::println);
        //mylist.stream().sorted(Comparator.comparingInt().forEach(System.out::println);
        Random random = new Random();
        //random.ints().limit(20).filter(i -> i > 0).forEach(a -> System.out.print(a + ",\t"));
        System.out.println("Sum of 20 Random ints " + random.ints().limit(20).mapToLong((i)-> i).summaryStatistics());
        List<Studentiit> myListStu = getStudentiitList();
        System.out.println("Streams Example takewhile maths marks > 63:");
        //myListStu.stream().takeWhile((Studentiit si) -> si.getMarksiit().getMaths() > 60 && si.getGender() == "M".charAt(0)).forEach(System.out::println);
        System.out.println("Streams Example dropwhile maths marks > 63:");
        //myListStu.stream().dropWhile(studentiit -> studentiit.getMarksiit().getMaths() < 90 && studentiit.getGender() == 'M' ).forEach(System.out::println);

        LocalDate dt = LocalDate.now();
        LocalDate dt1 = dt.plusDays(12);

        //myListStu.stream().forEach(System.out::println);
        System.out.println(myListStu.stream()
                .filter(si -> si.getGender() == 'F')
                .map(st -> st.getMarksiit().getChemistry())
                .filter(m -> m >90)
                .count());
                //.reduce(0, (tmpSum, val) -> tmpSum + 1));

        //System.out.println("Streams Sorted by Name:");
        //myListStu.stream().sorted((s1, s2) -> s1.getName().compareTo(s2.getName()))
        //        .collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("Min and Max of Total scores");
        System.out.println(myListStu.stream().max((s1, s2) -> s1.getMarksiit().getTotal().compareTo(s2.getMarksiit().getTotal()))
                .orElseThrow(NoSuchElementException::new));
        System.out.println(myListStu.stream().min(Comparator.comparing(Studentiit::getCity))
                .orElseThrow(NoSuchElementException::new));

        System.out.println("Joining Strings");
        // Method reference vrs lamda expression
        System.out.println(myListStu.stream().map(Studentiit::getName).sorted().collect(joining(", ")));
        // lamda expression  and aggregate operations intermediate operations (returns streams) terminal operation can alo be called Reduction
        // Reduction https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html
        // Parallelism  https://docs.oracle.com/javase/tutorial/collections/streams/parallelism.html
        // https://docs.oracle.com/javase/tutorial/collections/streams/examples/BulkDataOperationsExamples.java
        // lamda https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
        // terminal operations (such as average, sum, min, max, and count) that return one value by combining the contents of a stream
        // reduction operations that return a collection instead of a single value.  - reduce and Collect
        //

        System.out.println(myListStu.stream().map(s -> s.getName()).sorted().collect(joining(", ")));
        System.out.println(myListStu.stream().map(s -> s.getName()).sorted().collect(toSet()));
        //System.out.println(myListStu.stream().map(Studentiit::getName).collect(Collectors.toCollection(Vector<String>)));
        //System.out.println(myListStu.stream().collect(Collectors.summarizingLong(Collectors.summarizingLong())))
        iterate(1, i-> i < 1000, i -> i + i).forEach(System.out::print);
        System.out.println();

        Double ta = myListStu.stream().filter(st -> st.getGender() == 'M').mapToInt(st -> st.getMarksiit().getTotal()).average().getAsDouble();
        Integer ts = myListStu.stream().filter(st -> st.getGender() == 'M').mapToInt(st -> st.getMarksiit().getTotal()).sum();
        Integer tsa = myListStu.stream().filter(st -> st.getGender() == 'M').mapToInt(st -> st.getMarksiit().getTotal())
                .reduce(0,(a,b) -> a + b);
        // reduce operation takes two args identity (initial and default result) - accumulator - .collect(Averager::new, Averager::accept, Averager::combine);

        Integer totalsum = myListStu.stream().map(st -> st.getMarksiit().getTotal()).reduce(0, Integer::sum);
        Integer totalphy = myListStu.stream().map(st -> st.getMarksiit().getPhysics()).reduce(0, Integer::sum);
        Integer totalche = myListStu.stream().map(st -> st.getMarksiit().getChemistry()).reduce(0, Integer::sum);
        Integer totalmat = myListStu.stream().map(st -> st.getMarksiit().getMaths()).reduce(0, Integer::sum);

        System.out.println("Printing Individual of Total Marks: " + totalsum + "Phy: " + totalphy + "Che: " +totalche + "Mat: " +totalmat);
        System.out.println("Printing Division of Total Marks: " + (totalphy+totalche+totalmat)/totalsum);


        double avgp = Math.ceil(totalphy / (double)myListStu.size());
        double mid1 = avgp*0.75, mid2 = avgp*1.25;
        System.out.println("Avgp Marks: " + avgp);
        Integer cA =0, cB=0, cC=0, cD=0;

        for(Studentiit st:myListStu.stream().toList()) {
            if (st.getGender() == 'F') {
                if (st.getMarksiit().getPhysics() >= mid2) {
                    cA = cA +1;
                }
                if (st.getMarksiit().getPhysics() < mid2 && st.getMarksiit().getPhysics() >= avgp) {
                    cB = cB +1;
                }
                if (st.getMarksiit().getPhysics() < avgp && st.getMarksiit().getPhysics() >= mid1) {
                    cC = cC +1;
                }
                if (st.getMarksiit().getPhysics() < mid1) {
                    cD = cD + 1;
                }
            }
        }

        System.out.println("Printing count of mids cA: " + cA + "  cB: " + cB + "  cC: " +cC + "  cD: " +cD);
        //Float streamMapReduceClassMethosTotal = bill.getProducts().stream().map(Product::getCost).reduce((float) 0, Float::sum);

        // create stream from individual array elements
        //Stream.of(studentiitList);
        //studentiitList.stream();
        //Stream.Builder<Studentiit> studentiitBuilder = Stream.builder();
        //studentiitBuilder.accept(s7);   studentiitBuilder.accept(s12);  studentiitBuilder.accept(s15); studentiitBuilder.add(s18);
        //Stream<Studentiit> studentiitStream = studentiitBuilder.build();
        //studentiitStream.forEach(System.out::println);
        //studentiitStream.forEach();
        //empList.stream().forEach(e -> e.salaryIncrement(10.0));
        //Integer[] empIds = { 1, 5, 10 };
        //List<Studentiit> studentiits = (List<Studentiit>) Stream.of(empIds)
        //studentiits.stream().filter(studentiit -> studentiit.getCity() == "Erode")
        //        .map(studentiit -> studentiit.getGender() == 'M')
        //        .forEach(System.out::println);
        //.map(employeeRepository::findById).collect(Collectors.toList());
        //List<Employee> employees = empList.stream().collect(Collectors.toList());

        System.out.println("List students F with Physic marks > 90");
        List<Studentiit> stds = myListStu.stream().filter(f -> f.getCity() == "Chennai" && f.getMarksiit().getPhysics() > 80)
                .collect(toList());
        //.findFirst().orElse(null);
        //Employee[] employees = empList.stream().toArray(Employee[]::new);
        stds.forEach(System.out::println);

/*        List<List<String>> namesNested = Arrays.asList(
                Arrays.asList("Jeff", "Bezos"),
                Arrays.asList("Bill", "Gates"),
                Arrays.asList("Mark", "Zuckerberg"));

        List<String> namesFlatStream = namesNested.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        namesFlatStream.stream().forEach(System.out::println);*/

        //peek We saw forEach() earlier in this section, which is a terminal operation. However, sometimes we need to
        //perform multiple operations on each element of the stream before any terminal operation is applied.

        //List<Studentiit> stdsk = myListStu.stream().peek(e -> e.getCity())
        //        .peek(System.out::println)
        //        .collect(Collectors.toList());

        //stdsk.forEach(System.out::println);
        //Long empCount = empList.stream()
        //        .filter(e -> e.getSalary() > 200000)
        //        .count();
        // count of students who secured > 90 marks in Chemistry
        // short-circuiting operations  *****
/*        Stream<Integer> infiniteStream = Stream.iterate(2, i -> i * 2);
        Integer[] empIds = { 1, 2, 3, 4 };
        List<Integer> collect = infiniteStream
                .skip(3)
                .limit(5)
                .collect(Collectors.toList());*/
        // Lazy Evaluation
        // Computation on the source data is only performed when the terminal operation is initiated, and source elements are consumed only as needed.

        //assertEquals(empCount, new Long(1));
        //assertThat(empList, contains(
        //      hasProperty("salary", equalTo(110000.0)),
        //      hasProperty("salary", equalTo(220000.0)),
        //      hasProperty("salary", equalTo(330000.0))
        //    ));
        //assertEquals(employees.size(), empIds.length);
        //assertEquals(Arrays.asList(arrayOfEmps[2]), employees);
        //assertThat(empList.toArray(), equalTo(employees));
        //assertEquals(collect, Arrays.asList(16, 32, 64, 128, 256));
        //assertEquals(noneMultipleOfThree, false);
        //List<Integer> distinctIntList = intList.stream().distinct().collect(Collectors.toList());
        //List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);
        //boolean allEven = intList.stream().allMatch(i -> i % 2 == 0);
        //boolean oneEven = intList.stream().anyMatch(i -> i % 2 == 0);
        //boolean noneMultipleOfThree = intList.stream().noneMatch(i -> i % 3 == 0);
        //Java Stream Specializations - IntStream, LongStream, and DoubleStream extends base stream
        //IntStream.of(1, 2, 3);        Stream.of(1, 2, 3) returns Stream<Integer>
        //empList.stream().mapToDouble(Employee::getSalary).average().orElseThrow(NoSuchElementException::new)
        //Reduction Operations (also called as fold) - T reduce(T identity (Starting Value), BinaryOperator<T> accumulator(binary operation)
        //


        //Non-termincal operation filter, map, flatMap, distinct, limit, peek ,- concatenate streams
        //Terminal Operations  match(any, all, none) collect, count, find(any, all) forEach, min, max, reduce, toArray - parallel stream processing
    }

    /*
   S = [0, 1, 6, 7, 9, 10...]  for each c in S

   L = [0]
   LA = [[1], [1, 2], [1, 2, 3]]
   LB = [], LC = []
   foreach A in LA{   A = [1,2]
       foreach B in A{   B =2
           LB = [B] ++ LB   LB = [1]
           L = [first(L) + B]  L[2]
       }
       LC = [LB] ++ LC  =[1,1]
       LB = []
   }

Map<String, Choice> result =
    choices.stream().collect(Collectors.toMap(Choice::getName,  Function.identity()));
    Map<String, List<Choice>> result =
 choices.stream().collect(Collectors.groupingBy(Choice::getName));
 Map<String, Choice> result =
    choices.stream().collect(Collectors.toMap(Choice::getName, c -> c));

    //convert stream to Array -
    String[] strArray = stringStream.toArray(String[]::new)
    Stream<String> stringStream = Stream.of("a", "b", "c");



  public void stList(){
       int femCount = 0;
       int maleCount = 0;
       int highestMarks = 0;
       int highFemStudentiitMarks = 0;
       int highMaleStudentiitMarks = 0;

       ArrayList<String> cityList = new ArrayList<>();
       ArrayList<Integer> StudentiitMathsMarks = new ArrayList<>();

       for (Studentiit Studentiit : StudentiitList) {
           if (Studentiit.getGender() == 'F') {
               femCount += 1;
               if (highFemStudentiitMarks < Studentiit.getmarksiit().getTotal())
                   highFemStudentiitMarks = Studentiit.getMarks().getTotal();
           }
           else {
               maleCount += 1;
               if (highMaleStudentiitMarks < Studentiit.getMarks().getTotal())
                   highMaleStudentiitMarks = Studentiit.getMarks().getTotal();
           }
           if (!cityList.contains(Studentiit.getCity())) {
               cityList.add(Studentiit.getCity());
           }
           if (highestMarks < Studentiit.getMarks().getTotal())
               highestMarks = Studentiit.getMarks().getTotal();

           Integer cardNo = Studentiit.getCardNo();
           String StudentiitName = Studentiit.getName();
           System.out.println(cardNo + "  " + StudentiitName + "   \t" + Studentiit.getCity());

           StudentiitMathsMarks.add(Studentiit.getMarks().getMaths());

       }

       System.out.println(" SSSSXXX: " + StudentiitMathsMarks.stream().toList());

       for(Integer myMarks : StudentiitMathsMarks){
           Boolean isAvg = checkMarks(myMarks, StudentiitMathsMarks);
           System.out.print(isAvg);
       }

       HashMap<Integer, Integer> topThreeTotal = getTopThreeMarks("total");
       HashMap<Integer, Integer> topThreePhy = getTopThreeMarks("physics");
       HashMap<Integer, Integer> topThreeChe = getTopThreeMarks("chemistry");
       HashMap<Integer, Integer> topThreeMaths = getTopThreeMarks("maths");

       Boolean iSPhysicsTopper = isSubjectTopper(topThreePhy);

       // find if the topThreeTotal Studentiits satisfy the condition of at least one subject topper
       //
       Set<Integer> stSet = topThreeTotal.keySet();
       System.out.println("stSet is : " + stSet);
*//*
        for(int st=0; st<stSet.size();st++){
            if(checkForStuTopper(stSet.forEach(st))
            ;)){
            }
        }
*//*

        System.out.println("highFemStudentiitMarks: " + highFemStudentiitMarks + "  highMaleStudentiitMarks: " + highMaleStudentiitMarks + "  highestMarks: " + highestMarks);
        System.out.println("FemCount: " + femCount + " MaleCount: " + maleCount);
        System.out.println("Distinct City List: " + cityList);
        System.out.println(" Top Three Total Marks: " + topThreeTotal);
        //System.out.println(StudentiitList.stream().filter(Studentiit -> topThreeTotal.keySet().contains(Studentiit.getCardNo())).sorted((Studentiit1,Studentiit2)->Studentiit1.getMarks().getTotal().compareTo(Studentiit2.getMarks().getTotal())).collect(Collectors.toList()));
        System.out.println(" Top Three Phy: " + topThreePhy);
        //System.out.println(StudentiitList.stream().filter(Studentiit -> topThreePhy.keySet().contains(Studentiit.getCardNo())).sorted((Studentiit1,Studentiit2)->Studentiit1.getMarks().getPhysics().compareTo(Studentiit2.getMarks().getPhysics())).collect(Collectors.toList()));
        System.out.println(" Top Three Che: " + topThreeChe);
        //System.out.println(StudentiitList.stream().filter(Studentiit -> topThreeChe.keySet().contains(Studentiit.getCardNo())).sorted((Studentiit1,Studentiit2)->Studentiit1.getMarks().getChemistry().compareTo(Studentiit2.getMarks().getChemistry())).collect(Collectors.toList()));
        System.out.println(" Top Three Maths: " + topThreeMaths);
        //System.out.println(StudentiitList.stream().filter(Studentiit -> topThreeMaths.keySet().contains(Studentiit.getCardNo())).sorted((Studentiit1,Studentiit2)->Studentiit1.getMarks().getMaths().compareTo(Studentiit2.getMarks().getMaths())).collect(Collectors.toList()));
        //StudentiitList.stream().map(Studentiit::getMarks).forEach(System.out::println);
    }
*/
    Boolean checkMarks(Integer stuMarks, List<Integer> marksList){
        int cnt = 0;
        Boolean status = Boolean.FALSE;
        Integer maxCnt = marksList.size();

        while (cnt < maxCnt){
            int X = marksList.get(cnt);

            if (stuMarks > X) {
                status = Boolean.TRUE;
                break;
            }
            cnt = cnt + 1;
        }
        return status;
    }

    /*HashMap<Integer,Integer> getTopThreeMarks(String sub){
        int A = 0; int Aid = -1;
        int B = 0; int Bid = -1;
        int C = 0; int Cid = -1;
        int X = 0;

//        List<Integer> topThree = new ArrayList<>();
        for (Studentiit Studentiit : StudentiitList) {
            if ( sub.contains("total"))
                X = Studentiit.getMarks().getTotal();
            else if ( sub.contains("physics"))
                X = Studentiit.getMarks().getPhysics();
            else if (sub.contains("chemistry"))
                X = Studentiit.getMarks().getChemistry();
            else if (sub.contains("maths"))
                X = Studentiit.getMarks().getMaths();

            if (A < X) {
                C = B; Cid = Bid;
                B = A; Bid = Aid;
                A = X; Aid = Studentiit.getCardNo();
            } else if (B < X) {
                C = B; Cid = Bid;
                B = X; Bid = Studentiit.getCardNo();
            } else if (C < X) {
                C = X; Cid = Studentiit.getCardNo();
            }
        }

        HashMap<Integer,Integer> StudentiitIDMark = new HashMap<>();
        StudentiitIDMark.put(Aid,A);
        StudentiitIDMark.put(Bid,B);
        StudentiitIDMark.put(Cid,C);

        return StudentiitIDMark;
    }
    Boolean isSubjectTopper(HashMap<Integer, Integer> topThreePhy){
        boolean bl = true;
        return bl;
    }*/
}

package com.dcp.portone.leet.stream;

import java.time.LocalDate;
import java.util.*;


public class LinkedHashMaps {
    private static Map<String, PurchaseMap> purchases = new LinkedHashMap<>();
    private static NavigableMap<String, StudentMap> students = new TreeMap<>();

    public static void main(String[] args) {
        CourseMap jmc = new CourseMap("jmc101", "Java Master Class", "Java");
        CourseMap python = new CourseMap("pyt101", "Python Master Class", "Python");

        addPurchase("Mary Martin", jmc, 129.99);
        addPurchase("Andy Martin", jmc, 139.99);
        addPurchase("Mary Martin", python, 149.99);
        addPurchase("Joe Jones", jmc, 149.99);
        addPurchase("Bill Brown", python, 119.99);

        addPurchase("Chuck Cheese", python, 119.99);
        addPurchase("Davy Jones", jmc, 139.99);
        addPurchase("Eva East", python, 149.99);
        addPurchase("Fred Forker", jmc, 139.99);
        addPurchase("Greg Brady", python, 129.99);

        purchases.forEach((k,v)->System.out.println(k + ": " + v ));
        System.out.println("--".repeat(30));
        students.forEach((k,v)->System.out.println(k + ": " + v ));

        NavigableMap<LocalDate, List<PurchaseMap>> datedPurchases = new TreeMap<>();

        for (PurchaseMap p: purchases.values()) {
            datedPurchases.compute(p.purchaseDate(),
                    (pdate, plist) -> {
                        List<PurchaseMap> list = (plist == null) ? new ArrayList<>() : plist;
                        list.add(p);
                        return list;
                    });
        }
        datedPurchases.forEach((k,v)-> System.out.println(k + ": " + v));

        int currentYear = LocalDate.now().getYear();
        LocalDate firstDay = LocalDate.ofYearDay(currentYear, 1);
        LocalDate week1 = firstDay.plusDays(7);
        Map<LocalDate, List<PurchaseMap>> week1Purchases = datedPurchases.headMap(week1);
        Map<LocalDate, List<PurchaseMap>> week2Purchases = datedPurchases.tailMap(week1);

/*        System.out.println("--".repeat(30));
        week1Purchases.forEach((k,v)-> System.out.println(k + ": " + v));
        System.out.println("--".repeat(30));
        week2Purchases.forEach((k,v)-> System.out.println(k + ": " + v));*/

        dislayStats(1,week1Purchases);
        dislayStats(2,week2Purchases);

    }

    private static void addPurchase(String name, CourseMap course, double price) {
        StudentMap existingStudent = students.get(name);
        if (existingStudent == null) {
            existingStudent = new StudentMap(name, course);
            students.put(name, existingStudent);
        } else {
            existingStudent.addCourse(course);
        }

        int day = new Random().nextInt(1,15);
        String key = course.courseId() + "_" + existingStudent.getId();
        int year = LocalDate.now().getYear();
        PurchaseMap purchase = new PurchaseMap(course.courseId(), existingStudent.getId(), price, year, day);
        purchases.put(key, purchase);
    }

    private static void dislayStats(int period, Map<LocalDate, List<PurchaseMap>> periodData) {
        System.out.println("--".repeat(30));
        Map<String, Integer> weeklyCounts = new TreeMap<>();
        periodData.forEach((key, value)-> {
            System.out.println(key + ": " + value);
            for (PurchaseMap p: value) {
                weeklyCounts.merge(p.courseId(), 1, (prev, current) -> {
                    return prev + current;
                });
            }
        });
        System.out.printf("Week %d Purchases = %s%n", period, weeklyCounts);
    }
}

record CourseMap(String courseId, String name, String subject) {}
record PurchaseMap(String courseId, int StudentId, double price, int yr, int dayOfYear) {
    public LocalDate purchaseDate() {
        return LocalDate.ofYearDay(yr, dayOfYear);
    }

}
class StudentMap {
    public static int lastId = 1;
    private String name;
    private int id;
    private List<CourseMap> courseList;

    public StudentMap(String name, List<CourseMap> courseList) {
        this.name = name;
        this.courseList = courseList;
        this.id = lastId++;
    }
    public StudentMap(String name, CourseMap course) {
        this(name, new ArrayList<CourseMap>(List.of(course)));
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void addCourse(CourseMap course) {
        courseList.add(course);
    }

    @Override
    public String toString() {
        String[] courseNames = new String[courseList.size()];
        Arrays.setAll(courseNames, i -> courseList.get(i).name());
        return "[%d] : %s".formatted(id, String.join(", ", courseNames));
    }
}

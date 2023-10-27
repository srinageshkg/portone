package com.dcp.portone.corejava.file;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Main {
    public static void main(String[] args) {
        List<Employee> emps = new ArrayList<>(List.of(
                new Employee("Ralph", 10001, 2015),
                new Employee("Carole", 10005, 2021),
                new Employee("Jane", 10022, 2013),
                new Employee("Laura", 13151, 2020),
                new Employee("Jim", 10050, 2018)));

//        var comparator = new EmpComp<>();
//        emps.sort(comparator);
        emps.sort(new Employee.EmpComp<>("empId").reversed());

        for(Employee e : emps) {
            System.out.println(e);
        }

        System.out.println("Printing Store Employees.");
        List<StoreEmployee> storeEmployees = new ArrayList<>(List.of(
                new StoreEmployee("Meg",10015,2019,"Target"),
                new StoreEmployee("Joe",10515,2021,"Walmart"),
                new StoreEmployee("Tom",10105,2020,"Macys"),
                new StoreEmployee("Marty",10215,2018,"Walmart"),
                new StoreEmployee("Bud",10322,2016,"Target")));

        // To create an instance of inner class, first have an instance of enclosing class
        // from this instance call .new followed by inner class name and then parenthesis
        // taking any constructor args
        // EnclosingClass outerClass = new EnclosingCLass();
        // EnclosingClass.InnerClass innerClass = outerClasss.new InnerClass()
        // storeEmployees.sort(new Employee.EmpComp<>("yrStarted"));
        var genericEmp = new StoreEmployee();
        storeEmployees.sort(genericEmp.new StoreComparator<>());
        for(StoreEmployee e : storeEmployees) {
            System.out.println(e);
        }
    }
}
/*class EmpComp <T extends Employee> implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getName().compareTo(o2.getName());
    }
}*/
class Employee {
    private String name;
    private int empId;
    private int yrStarted;

    public static class EmpComp <T extends Employee> implements Comparator<Employee> {
        private String sortType;
        public EmpComp() {
            this("name");
        }
        public EmpComp(String sortType) {
            this.sortType = sortType;
        }

        @Override
        public int compare(Employee o1, Employee o2) {
            if (sortType.equalsIgnoreCase("yrStarted")) {
                return o1.yrStarted - o2.yrStarted;
            } else if (sortType.equalsIgnoreCase("empId")) {
                return o1.empId - o2.empId;
            }
            return o1.name.compareTo(o2.name);
        }
    }
    public Employee() {
    }

    public String getName() {
        return name;
    }

    public Employee(String name, int empId, int yrStarted) {
        this.name = name;
        this.empId = empId;
        this.yrStarted = yrStarted;
    }

    @Override
    public String toString() {
        return "%-8s Id: %d working since Yr %d".formatted(name, empId, yrStarted);
    }
}

class StoreEmployee extends Employee {
    private String store;
    public StoreEmployee() {
    }
    public StoreEmployee(String name, int empId, int yrStarted, String store) {
        super(name, empId, yrStarted);
        this.store = store;
    }
    @Override
    public String toString() {
        return "%-8s%s".formatted(store, super.toString());
    }

    public class StoreComparator<T extends StoreEmployee> implements Comparator<StoreEmployee> {

        @Override
        public int compare(StoreEmployee o1, StoreEmployee o2) {
            int result = o1.store.compareTo(o2.store);
            if (result == 0) {
                //return new Employee.EmpComp<>("yrStarted").compare(o1,o2);
                return new Employee.EmpComp<>().compare(o1,o2);
            }
            return result;
        }
    }
}
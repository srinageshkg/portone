package com.dcp.portone.practice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class SportPerson {
    private String name;
}

@NoArgsConstructor
@AllArgsConstructor
class AggCountry {
    private String name;
    List<SportPerson> sportPersons;
    public List<String> getSportPersons() {
        List<SportPerson> sportPersonList = this.sportPersons;
        List<String> names = new ArrayList<>();
        for (SportPerson sp : sportPersonList) {
            names.add(sp.getName());
        }
        return names;
    }
}
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Student{
    private String name;
}
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class CompCollege {
    private String name;
    private List<Student> studentList;
    public List<String> getStudentList() {
        List<Student> studentList1 = this.studentList;
        List<String> names = new ArrayList<>();
        for (Student st: studentList1) {
            names.add(st.getName());
        }
        return names;
    }
    public void setStudentList() {
        Student s1 = new Student("ST1");
        Student s2 = new Student("ST2");
        Student s3 = new Student("ST3");

        List<Student> stuList= new ArrayList<>(Arrays.asList(s1,s2,s3));
        this.studentList = stuList;
    }
    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}

class AggDemo {
    public static  void display() {
        SportPerson sp1 = new SportPerson("SP1");
        SportPerson sp2 = new SportPerson("SP2");
        SportPerson sp3 = new SportPerson("SP3");

        List<SportPerson> spList = new ArrayList<>(Arrays.asList(sp1, sp2, sp3));
        AggCountry aggCountry = new AggCountry("USA", spList);

        System.out.println("Aggregation Ex: " + aggCountry.getSportPersons());

        CompCollege compCollege = new CompCollege();
        compCollege.setName("UTD");
        compCollege.setStudentList();

        Student s1 = new Student("ST4");
        Student s2 = new Student("ST5");
        Student s3 = new Student("ST6");
        List<Student> stuList= new ArrayList<>(Arrays.asList(s1,s2,s3));
        CompCollege compCollege1 = new CompCollege("ATA", stuList);

        System.out.println("Composition Ex1: " + compCollege.getStudentList());
        System.out.println("Composition Ex2: " + compCollege1.getStudentList());
    }

    public static void main(String[] args) {
        List<int[]> intList = new ArrayList<>((Arrays.asList(new int[]{1,2,3}, new int[]{5,6,7}, new int[]{9,1,4})));
        for ( var l : intList) {
            System.out.print("  l = " + Arrays.toString(l));
            System.out.print("  l[2] = " + l[2]);
        }
        display();
    }
}

package com.dcp.portone.practice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Institute {

    private String name;
    @Autowired
    private Set<Dept> deptsSet;

    public void display(){
        System.out.println("Name of the Inst : " + name);
        //Set<Dept> deptSetNew = deptsSet.stream().sorted();
        //List<Classroom> listcl = deptsSet.stream().sorted().map(Dept::getClassroomList).collect(Collectors.toList());
        for (Dept d: deptsSet) {
            System.out.println("\tName of the Dept : " + d.getDept());
            System.out.print("\t\tNames of the Classroom\t\t\tType\n");
            for (Classroom c: d.getClassroomList()){
                System.out.format("%30s  %15s %n", c.getName() , c.getType());
            }
            System.out.print("\t\t\tNames of the Prof \t\t\tRank\t\tSubject\n");
            for (Prof p: d.getProfs()){
                 System.out.format("%30s %15s %12s%n", p.getName(),p.getRank(),p.getSubject());
            }
            System.out.print("\t\t\t\tName of the Student \t\t\t  Group\n");
            for (Stud s: d.getStudList()){
                 System.out.format("%34s %18s%n",s.getName(), s.getDept());
            }
        }
    }
    public void displayfilter(){
        System.out.println("Name of the Inst : " + name);
        //Set<Dept> deptSetNew = deptsSet.stream().sorted();
        //List<Classroom> listcl = deptsSet.stream().sorted().map(Dept::getClassroomList).collect(Collectors.toList());
        //for (Dept d: deptsSet.stream().collect(Collectors.toList())) {
        for (Dept d: deptsSet.stream().filter(dp -> dp.getDept() == Dept.Depts.Mech).collect(Collectors.toList())) {

            System.out.println("\tName of the Dept : " + d.getDept());
            System.out.print("\t\tNames of the Classroom\t\t\tType\n");
            for (Classroom c: d.getClassroomList()){
                System.out.format("%30s  %15s %n", c.getName() , c.getType());
            }
            System.out.print("\t\t\tNames of the Prof \t\t\tRank\t\tSubject\n");
            for (Prof p: d.getProfs()){
                System.out.format("%30s %15s %12s%n", p.getName(),p.getRank(),p.getSubject());
            }
            System.out.print("\t\t\t\tName of the Student \t\t\t  Group\n");
            for (Stud s: d.getStudList()){
                System.out.format("%34s %18s%n",s.getName(), s.getDept());
            }
        }
    }

    @Bean
    public void createInst(){
        Classroom classroom1 = new Classroom("CSE1", Classroom.CRTypes.LECTURE);
        Classroom classroom2 = new Classroom("CSE2", Classroom.CRTypes.DEMO);
        Classroom classroom3 = new Classroom("Mech1", Classroom.CRTypes.LECTURE);
        Classroom classroom4 = new Classroom("Mech2", Classroom.CRTypes.LAB);

        List<Classroom> classroomList = List.of(classroom1, classroom2, classroom3, classroom4);

        List<Classroom> crCse = new ArrayList<>();
        classroomList.stream().forEachOrdered(crCse::add);
        List<Classroom> classroomCseList = List.of(classroom1, classroom2);
        List<Classroom> classroomMechList = List.of(classroom3, classroom4);

        Prof prof1 = new Prof("Paul", "CT", Prof.PRank.SENIOR);
        Prof prof2 = new Prof("Tim", "Design", Prof.PRank.DEAN);
        Prof prof3 = new Prof("Larry", "CT", Prof.PRank.JUNIOR);

        Set<Prof> profSet = Set.of(prof1, prof3);
        Set<Prof> profMechSet = Set.of(prof2);

        Stud stud1 = new Stud("Rahul", Dept.Depts.CSE);
        Stud stud2 = new Stud("Prabhu", Dept.Depts.CSE);
        Stud stud3 = new Stud("Geeta", Dept.Depts.Mech);
        Stud stud4 = new Stud("Vineet", Dept.Depts.Mech);

        List<Stud> studListcse = new ArrayList<>();
        List<Stud> studListmech = new ArrayList<>();
        Collections.addAll(studListcse, stud1, stud2);
        Collections.addAll(studListmech, stud3, stud4);

        Dept deptCse = new Dept(Dept.Depts.CSE, classroomCseList, profSet, studListcse);
        //Dept deptMech = new Dept(Dept.Depts.Mech, classroomMechList, profMechSet, studListmech);
        Dept deptMech = new Dept();
        deptMech.setDept(Dept.Depts.Mech);
        deptMech.setProfs(profMechSet);
        deptMech.setClassroomList(classroomMechList);
        deptMech.setStudList(studListmech);
        Set<Dept> deptSet = new HashSet<>();
        deptSet.add(deptCse); deptSet.add(deptMech);

        Institute inst = new Institute("IITM", deptSet);
        inst.display();
    }
}



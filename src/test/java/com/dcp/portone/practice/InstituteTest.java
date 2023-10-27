package com.dcp.portone.practice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InstituteTest {
    @Test
    public void saveInst(){

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
        inst.displayfilter();

    }
}
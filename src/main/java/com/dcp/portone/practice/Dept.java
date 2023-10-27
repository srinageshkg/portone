package com.dcp.portone.practice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
@Service
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
    public enum Depts {CSE, Mech, ECE, Civil}
    private Depts dept;
    @Autowired
    private List<Classroom> classroomList;
    @Autowired
    private Set<Prof> profs;
    @Autowired
    private List<Stud> studList;

}

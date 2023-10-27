package com.dcp.portone.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "org")
public class Org {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "org_seq_gen")
    @SequenceGenerator(name = "org_seq_gen", sequenceName = "org_seq")
    private Long Id;

    private String name;
    private String location;

    //@OneToMany(mappedBy = "org", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Set<Emp> emps;

}

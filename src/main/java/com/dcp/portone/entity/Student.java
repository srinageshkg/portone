package com.dcp.portone.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table (name = "students", uniqueConstraints =
        {   @UniqueConstraint(name = "cardNo_unique", columnNames = "cardNo"),
            @UniqueConstraint(name = "name_unique", columnNames = "name")
        })
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    private Integer cardNo;
    private String name;
    private String gender;
    private String dateOfBirth;
    private String cityTown;
    @Embedded
    private Marks marks;

}

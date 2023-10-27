package com.dcp.portone.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "words", uniqueConstraints = @UniqueConstraint(name = "wordNo_unique", columnNames = "wordNo"))

public class Word {
    @Id
    @SequenceGenerator(name = "sequence_generator", sequenceName = "sequence_generator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    private Long wordSeqNo;
    private Integer wordNo;
    private String word;
    private String partOfSpeech;
    private Integer letterCount;

}
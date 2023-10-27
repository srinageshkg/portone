package com.dcp.portone.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@AttributeOverrides({
        @AttributeOverride(
                name = "mathsMarks",
                column = @Column(name = "maths")
        ),
        @AttributeOverride(
                name = "physicsMarks",
                column = @Column(name = "physics")
        ),
        @AttributeOverride(
                name = "chemistryMarks",
                column = @Column(name = "chemistry")
        ),
        @AttributeOverride(
                name = "totalMarks",
                column = @Column(name = "total")
        )
})
public class Marks {

/*    @Id
    @SequenceGenerator(name = "marks_sequence_generator", sequenceName = "marks_sequence_generator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marks_sequence_generator")
    private Long seqId;*/
    private Integer mathsMarks;
    private Integer physicsMarks;
    private Integer chemistryMarks;
    private Integer totalMarks;
}

package com.dcp.portone.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_seq_gen")
    @SequenceGenerator(name = "store_seq_gen", sequenceName = "store_seq")
    private Long Id;
    private String storeName;
    private String location;

    //@OneToOne(mappedBy = "store", fetch = FetchType.LAZY)
    //private Shoppingbill shoppingbill;
}

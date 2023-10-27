package com.dcp.portone.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "shoppingbills")
public class Shoppingbill {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill_seq_gen")
    @SequenceGenerator(name = "bill_seq_gen", sequenceName = "bill_seq")
    private Long id;
    private String storeName;
    private String customerName;
    @CreationTimestamp
    private Date dateCreated;
    @UpdateTimestamp
    private Date dateUpdated;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shoppingbills_id", referencedColumnName = "id")
    private Set<Product> products;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stores_id", referencedColumnName = "id")
    private Store store;
    //private Set<Product> products = new HashSet<>();
    //@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "shoppingbill", orphanRemoval = true)

}
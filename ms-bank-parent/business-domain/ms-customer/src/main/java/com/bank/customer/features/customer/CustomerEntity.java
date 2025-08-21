package com.bank.customer.features.customer;

import com.bank.customer.common.BaseEntity;
import com.bank.customer.features.person.PersonEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "customers")
@Entity
public class CustomerEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId //Indicates that this property is assigned to the primary key
    @JoinColumn(name = "person_id")
    private PersonEntity personEntity;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone_number;

    @Column(nullable = false)
    private CustomerConstants.Status status;
}
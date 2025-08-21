package com.bank.customer.features.person;

import com.bank.customer.common.BaseEntity;
import com.bank.customer.features.customer.CustomerEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "persons")
@Entity
public class PersonEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String first_name;

  @Column(nullable = false)
  private String last_name;

  @Column(nullable = false)
  private PersonConstants.DocumentType document_type;

  @Column(nullable = false)
  private String document_number;

  @OneToMany(mappedBy = "personEntity", fetch = FetchType.LAZY)
  private List<CustomerEntity> customerEntities  = new ArrayList<>();
}
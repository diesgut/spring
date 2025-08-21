package com.bank.bankaccount.features.bankaccount;

import com.bank.bankaccount.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "bank_accounts")
@Entity
public class BankAccountEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private UUID customer_uuid;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private BankAccountConstants.Banks bank;

  @Column(nullable = false)
  private BigDecimal total_balance;

  @Column(nullable = false)
  private BankAccountConstants.Status status;
}
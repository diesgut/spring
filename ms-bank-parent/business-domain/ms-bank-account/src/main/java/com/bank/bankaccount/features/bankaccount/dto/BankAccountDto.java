package com.bank.bankaccount.features.bankaccount.dto;

import com.bank.bankaccount.features.bankaccount.BankAccountConstants;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.bank.bankaccount.features.bankaccount.BankAccountEntity}
 */
public record BankAccountDto(UUID transactionUuid, LocalDateTime createdAt, LocalDateTime updatedAt,
                             UUID customerUuid, BankAccountConstants.Banks bank, BigDecimal totalBalance,
                             BankAccountConstants.Status status) implements Serializable {
}
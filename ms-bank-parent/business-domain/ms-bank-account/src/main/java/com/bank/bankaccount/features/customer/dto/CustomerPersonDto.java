package com.bank.bankaccount.features.customer.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.bank.customer.features.person.PersonEntity}
 */
public record CustomerPersonDto(@NotNull UUID transactionUuid) implements Serializable {
}
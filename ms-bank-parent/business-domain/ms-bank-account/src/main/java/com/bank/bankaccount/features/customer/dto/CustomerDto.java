package com.bank.bankaccount.features.customer.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerDto(@NotNull UUID transactionUuid, @NotNull LocalDateTime createdAt,
                          @NotNull LocalDateTime updatedAt, @NotNull CustomerPersonDto person, @NotNull String company,
                          @NotNull String email, @NotNull String phoneNumber,
                          String status) implements Serializable {
}
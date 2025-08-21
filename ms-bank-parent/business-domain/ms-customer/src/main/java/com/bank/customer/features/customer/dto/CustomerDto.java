package com.bank.customer.features.customer.dto;

import com.bank.customer.features.customer.CustomerConstants;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.bank.customer.features.customer.CustomerEntity}
 */
public record CustomerDto(@NotNull UUID transactionUuid, @NotNull LocalDateTime createdAt,
                          @NotNull LocalDateTime updatedAt, @NotNull CustomerPersonDto person, @NotNull String company,
                          @NotNull String email, @NotNull String phoneNumber,
                          @NotNull CustomerConstants.Status status) implements Serializable {
}
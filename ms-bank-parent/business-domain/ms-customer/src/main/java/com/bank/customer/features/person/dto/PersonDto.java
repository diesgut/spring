package com.bank.customer.features.person.dto;

import com.bank.customer.features.person.PersonConstants;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public record PersonDto(UUID transactionUuid, LocalDateTime createdAt, LocalDateTime updatedAt, String firstName,
                        String lastName, @NotNull PersonConstants.DocumentType documentType, String documentNumber) implements Serializable {
}
package com.bank.customer.features.person.dto;

import com.bank.customer.features.person.PersonConstants;
import com.bank.customer.features.person.PersonEntity;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link PersonEntity}
 */
public record CreatePersonDto(@NotNull String firstName, @NotNull String lastName, @NotNull PersonConstants.DocumentType documentType, @NotNull String documentNumber) implements Serializable {
  }
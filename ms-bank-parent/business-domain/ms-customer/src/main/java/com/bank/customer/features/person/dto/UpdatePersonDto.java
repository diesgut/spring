package com.bank.customer.features.person.dto;

import com.bank.customer.features.person.PersonConstants;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record UpdatePersonDto(String firstName, String lastName, @NotNull PersonConstants.DocumentType documentType, String documentNumber) implements Serializable {
}
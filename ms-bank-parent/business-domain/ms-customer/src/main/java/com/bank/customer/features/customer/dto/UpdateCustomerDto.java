package com.bank.customer.features.customer.dto;

import com.bank.customer.features.customer.CustomerConstants;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.bank.customer.features.customer.CustomerEntity}
 */
public record UpdateCustomerDto(@NotNull CustomerPersonDto person, @NotNull String company, @NotNull String email, @NotNull String phoneNumber,
                                @NotNull CustomerConstants.Status status) implements Serializable {
}
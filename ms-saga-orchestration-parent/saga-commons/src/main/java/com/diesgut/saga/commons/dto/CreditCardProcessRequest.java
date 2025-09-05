package com.diesgut.saga.commons.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardProcessRequest {
    @NotNull
    @Positive
    private BigInteger creditCardNumber;
    @NotNull
    @Positive
    private BigDecimal paymentAmount;
}

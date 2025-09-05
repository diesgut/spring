package com.diesgut.saga.order.doman.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    @NotNull
    private UUID customerId;
    @NotNull
    private UUID productId;
    @NotNull
    @Positive
    private Integer productQuantity;
}

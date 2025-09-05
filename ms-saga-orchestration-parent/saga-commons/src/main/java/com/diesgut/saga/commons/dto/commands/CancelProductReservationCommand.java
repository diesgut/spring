package com.diesgut.saga.commons.dto.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelProductReservationCommand {
    private UUID productId;
    private UUID orderId;
    private Integer productQuantity;
}

package com.diesgut.saga.commons.dto.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReservationFailedEvent {
    private UUID productId;
    private UUID orderId;
    private Integer productQuantity;
}

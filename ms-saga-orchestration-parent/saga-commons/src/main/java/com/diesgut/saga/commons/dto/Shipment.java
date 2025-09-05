package com.diesgut.saga.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {
    private UUID id;
    private UUID orderId;
    private UUID paymentId;
}

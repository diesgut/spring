package com.diesgut.saga.order.doman.order_history.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistory {
    private UUID id;
    private UUID orderId;
    private com.diesgut.saga.commons.types.OrderStatus status;
    private Timestamp createdAt;
}

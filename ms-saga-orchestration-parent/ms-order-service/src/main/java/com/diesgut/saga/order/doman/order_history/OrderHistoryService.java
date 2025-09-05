package com.diesgut.saga.order.doman.order_history;

import com.diesgut.saga.commons.types.OrderStatus;
import com.diesgut.saga.order.doman.order_history.dto.OrderHistory;

import java.util.List;
import java.util.UUID;

public interface OrderHistoryService {
    void add(UUID orderId, OrderStatus orderStatus);

    List<OrderHistory> findByOrderId(UUID orderId);
}

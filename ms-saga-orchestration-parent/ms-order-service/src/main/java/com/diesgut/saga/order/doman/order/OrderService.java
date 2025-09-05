package com.diesgut.saga.order.doman.order;

import com.diesgut.saga.commons.dto.Order;

import java.util.UUID;

public interface OrderService {
    Order placeOrder(Order order);
    void approveOrder(UUID orderId);
    void rejectOrder(UUID orderId);
}

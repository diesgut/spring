package com.diesgut.saga.order.doman.order;

import com.diesgut.saga.commons.dto.Order;
import com.diesgut.saga.order.doman.order.dto.CreateOrderRequest;
import com.diesgut.saga.order.doman.order.dto.CreateOrderResponse;
import com.diesgut.saga.order.doman.order_history.OrderHistoryService;
import com.diesgut.saga.order.doman.order_history.dto.OrderHistoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderHistoryService orderHistoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CreateOrderResponse placeOrder(@RequestBody @Valid CreateOrderRequest request) {
        var order = new Order();
        BeanUtils.copyProperties(request, order);
        Order createdOrder = orderService.placeOrder(order);

        var response = new CreateOrderResponse();
        BeanUtils.copyProperties(createdOrder, response);
        return response;
    }

    @GetMapping("/{orderId}/history")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderHistoryResponse> getOrderHistory(@PathVariable UUID orderId) {
        return orderHistoryService.findByOrderId(orderId).stream().map(orderHistory -> {
            OrderHistoryResponse orderHistoryResponse = new OrderHistoryResponse();
            BeanUtils.copyProperties(orderHistory, orderHistoryResponse);
            return orderHistoryResponse;
        }).toList();
    }
}

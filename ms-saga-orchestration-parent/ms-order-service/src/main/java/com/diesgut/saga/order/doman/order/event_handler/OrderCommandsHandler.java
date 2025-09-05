package com.diesgut.saga.order.doman.order.event_handler;

import com.diesgut.saga.commons.dto.commands.ApproveOrderCommand;
import com.diesgut.saga.commons.dto.commands.RejectOrderCommand;
import com.diesgut.saga.order.doman.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@KafkaListener(topics="${orders.commands.topic.name}")
public class OrderCommandsHandler {
    private final OrderService orderService;

    @KafkaHandler
    public void handleCommand(@Payload ApproveOrderCommand approveOrderCommand) {
        orderService.approveOrder(approveOrderCommand.orderId());
    }

    @KafkaHandler
    public void handleCommand(@Payload RejectOrderCommand rejectOrderCommand) {
        orderService.rejectOrder(rejectOrderCommand.orderId());
    }
}

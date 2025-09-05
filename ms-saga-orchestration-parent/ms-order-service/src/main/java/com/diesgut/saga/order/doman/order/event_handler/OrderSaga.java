package com.diesgut.saga.order.doman.order.event_handler;

import com.diesgut.saga.commons.dto.commands.*;
import com.diesgut.saga.commons.dto.events.*;
import com.diesgut.saga.commons.types.OrderStatus;
import com.diesgut.saga.order.doman.order_history.OrderHistoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics={
        "${orders.events.topic.name}",
        "${products.events.topic.name}",
        "${payments.events.topic.name}"
})
public class OrderSaga {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final OrderHistoryService orderHistoryService;

    private final String productsCommandsTopicName;
    private final String paymentsCommandsTopicName;
    private final String ordersCommandsTopicName;

    public OrderSaga(
            KafkaTemplate<String, Object> kafkaTemplate,
            OrderHistoryService orderHistoryService,
            @Value("${products.commands.topic.name}") String productsCommandsTopicName,
            @Value("${payments.commands.topic.name}") String paymentsCommandsTopicName,
            @Value("${orders.commands.topic.name}") String ordersCommandsTopicName
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderHistoryService = orderHistoryService;
        this.productsCommandsTopicName = productsCommandsTopicName;
        this.paymentsCommandsTopicName = paymentsCommandsTopicName;
        this.ordersCommandsTopicName = ordersCommandsTopicName;
    }

    //Listener to orders.events.topic.name
    @KafkaHandler
    public void handleEvent(@Payload OrderCreatedEvent event) {

        ReserveProductCommand command = new ReserveProductCommand(
                event.getProductId(),
                event.getProductQuantity(),
                event.getOrderId()
        );

        kafkaTemplate.send(productsCommandsTopicName,command);
        orderHistoryService.add(event.getOrderId(), OrderStatus.CREATED);
    }

    //Listener to products.events.topic.name
    @KafkaHandler
    public void handleEvent(@Payload ProductReservedEvent event) {

        ProcessPaymentCommand processPaymentCommand = new ProcessPaymentCommand(event.getOrderId(),
                event.getProductId(),event.getProductPrice(), event.getProductQuantity());
        kafkaTemplate.send(paymentsCommandsTopicName,processPaymentCommand);
    }

    //Listener to products.events.topic.name
    @KafkaHandler
    public void handleEvent(@Payload ProductReservationFailedEvent event) {
        RejectOrderCommand rejectOrderCommand = new RejectOrderCommand(event.getOrderId());
        kafkaTemplate.send(ordersCommandsTopicName, rejectOrderCommand);
        orderHistoryService.add(event.getOrderId(), OrderStatus.REJECTED);
    }

    //Listener to products.events.topic.name
    @KafkaHandler
    public void handleEvent(@Payload ProductReservationCancelledEvent event) {
        RejectOrderCommand rejectOrderCommand = new RejectOrderCommand(event.getOrderId());
        kafkaTemplate.send(ordersCommandsTopicName, rejectOrderCommand);
        orderHistoryService.add(event.getOrderId(), OrderStatus.REJECTED);
    }

    //Listener to payments.events.topic.name
    @KafkaHandler
    public void handleEvent(@Payload PaymentProcessedEvent event) {

        ApproveOrderCommand approveOrderCommand = new ApproveOrderCommand(event.getOrderId());
        kafkaTemplate.send(ordersCommandsTopicName,approveOrderCommand);
    }

    //Listener to payments.events.topic.name
    @KafkaHandler
    public void handleEvent(@Payload PaymentFailedEvent event) {
        CancelProductReservationCommand cancelProductReservationCommand =
                new CancelProductReservationCommand(event.getProductId(),
                        event.getOrderId(),
                        event.getProductQuantity());
        kafkaTemplate.send(productsCommandsTopicName, cancelProductReservationCommand);
    }

    //Listener to orders.events.topic.name
    @KafkaHandler
    public void handleEvent(@Payload OrderApprovedEvent event) {
        orderHistoryService.add(event.orderId(), OrderStatus.APPROVED);
    }
}

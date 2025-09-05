package com.diesgut.saga.payment.domain.payment.event_handler;

import com.diesgut.saga.commons.dto.Payment;
import com.diesgut.saga.commons.dto.commands.ProcessPaymentCommand;
import com.diesgut.saga.commons.dto.events.PaymentFailedEvent;
import com.diesgut.saga.commons.dto.events.PaymentProcessedEvent;
import com.diesgut.saga.commons.exceptions.CreditCardProcessorUnavailableException;
import com.diesgut.saga.payment.domain.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@KafkaListener(topics = "${payments.commands.topic.name}")
public class PaymentsCommandsHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PaymentService paymentService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${payments.events.topic.name}")
    private final String paymentEventsTopicName;


    @KafkaHandler
    public void handleCommand(@Payload ProcessPaymentCommand command) {

        try {
            Payment payment = Payment.builder().orderId(command.getOrderId())
                    .productId(command.getProductId())
                    .productPrice(command.getProductPrice())
                    .productQuantity(command.getProductQuantity())
                    .build();
            Payment processedPayment = paymentService.process(payment);
            PaymentProcessedEvent paymentProcessedEvent = new PaymentProcessedEvent(processedPayment.getOrderId(),
                    processedPayment.getId());
            kafkaTemplate.send(paymentEventsTopicName, paymentProcessedEvent);
        } catch (CreditCardProcessorUnavailableException e) {
            logger.error(e.getLocalizedMessage(), e);
            PaymentFailedEvent paymentFailedEvent = new PaymentFailedEvent(command.getOrderId(),
                    command.getProductId(),
                    command.getProductQuantity());
            kafkaTemplate.send(paymentEventsTopicName, paymentFailedEvent);
        }
    }
}

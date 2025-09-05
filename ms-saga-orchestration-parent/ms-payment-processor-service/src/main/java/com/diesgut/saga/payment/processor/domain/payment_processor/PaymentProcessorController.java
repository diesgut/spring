package com.diesgut.saga.payment.processor.domain.payment_processor;

import com.diesgut.saga.commons.dto.CreditCardProcessRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ccp")
public class PaymentProcessorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentProcessorController.class);

    @PostMapping("/process")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void processCreditCard(@RequestBody @Valid CreditCardProcessRequest request) {
        LOGGER.info("Processing request: {}", request);
    }
}

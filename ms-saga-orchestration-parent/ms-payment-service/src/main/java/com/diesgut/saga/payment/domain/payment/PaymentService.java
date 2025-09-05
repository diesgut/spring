package com.diesgut.saga.payment.domain.payment;
import com.diesgut.saga.commons.dto.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> findAll();

    Payment process(Payment payment);
}

package com.diesgut.saga.payment.domain.payment;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface CreditCardProcessorRemoteService {
    void process(BigInteger cardNumber, BigDecimal paymentAmount);
}

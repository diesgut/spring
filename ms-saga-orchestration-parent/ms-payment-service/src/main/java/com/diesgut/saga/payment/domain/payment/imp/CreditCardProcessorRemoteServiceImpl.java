package com.diesgut.saga.payment.domain.payment.imp;

import com.diesgut.saga.commons.dto.CreditCardProcessRequest;
import com.diesgut.saga.commons.exceptions.CreditCardProcessorUnavailableException;
import com.diesgut.saga.payment.domain.payment.CreditCardProcessorRemoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.BigInteger;

@RequiredArgsConstructor
@Service
public class CreditCardProcessorRemoteServiceImpl implements CreditCardProcessorRemoteService {
    private final RestTemplate restTemplate;

    @Value("${remote.ccp.url}")
    private final String ccpRemoteServiceUrl;

    @Override
    public void process(BigInteger cardNumber, BigDecimal paymentAmount) {
        try {
            var request = CreditCardProcessRequest.builder().creditCardNumber(cardNumber).paymentAmount(paymentAmount).build();
            restTemplate.postForObject(ccpRemoteServiceUrl + "/ccp/process", request, CreditCardProcessRequest.class);
        } catch (ResourceAccessException e) {
            throw new CreditCardProcessorUnavailableException(e);
        }
    }
}

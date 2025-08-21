package com.bank.bankaccount.features.customer;


import com.bank.bankaccount.features.customer.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Slf4j
@Component
public class CustomerWfClient {
    private WebClient webClient;

    public CustomerWfClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://MS-CUSTOMER")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                //   .defaultUriVariables(Collections.singletonMap("url", "http://MS-CUSTOMER/ms-customer/api/v1/customers"))
                .build();
    }


    public CustomerDto findCustomerById(UUID customerUuid) {
        return webClient.method(HttpMethod.GET).uri("/customer-service/api/v1/customers/{customerUuid}", customerUuid)
                .retrieve().bodyToMono(CustomerDto.class).block();
    }
}

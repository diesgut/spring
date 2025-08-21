package com.bank.customer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Slf4j
@Configuration
public class LoadBalanceConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalanceWebClientBuilder() {
        return WebClient.builder();
    }
}

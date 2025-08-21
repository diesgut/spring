package com.bank.bankaccount.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class GlobalWebClientConfig {
    @Bean
    public WebClientCustomizer globalCustomizer(ReactorLoadBalancerExchangeFilterFunction lb) {
        return webClientBuilder -> {
            webClientBuilder
                    .filter(logRequest())
                    .filter(lb);
        };
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.debug("Method {}, URL {}", clientRequest.method(), clientRequest.url());
            // Es importante retornar el request para que la cadena continúe
            return Mono.just(clientRequest);
        });
    }
}

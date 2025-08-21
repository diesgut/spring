package com.bank.gateway.springgateway.filters;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final WebClient.Builder webclientBuilder;

    public AuthenticationFilter(WebClient.Builder webclientBuilder) {
        super(Config.class);
        this.webclientBuilder = webclientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {

        return new OrderedGatewayFilter((exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing  Authorization header");
            }


            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String[] parts = authHeader.split(" ");
            if (parts.length != 2 || !"Bearer".equals(parts[0])) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad Authorization structure");
            }

            return  webclientBuilder
                    .filter(logRequest())
                    .build()
                    .get()
                    .uri(config.getValidationUri())
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + parts[1])
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .map(response -> {
                        if(response != null){
                            log.info("See Objects: " + response);
                            //check for Partners rol
                            if(response.get(config.getRequiredRole()) == null || StringUtils.isEmpty(response.get(config.getRequiredRole()).asText())){
                                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Role Partners missing");
                            }
                        }else{
                            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Roles missing");
                        }
                        return exchange;
                    })
                    .onErrorMap(error -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Communication Error", error.getCause());})
                    .flatMap(chain::filter);
        },1);
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.debug("Method {}, URL {}, HEADER {}", clientRequest.method(), clientRequest.url(), clientRequest.headers().get(HttpHeaders.AUTHORIZATION));
            // Es importante retornar el request para que la cadena continúe
            return Mono.just(clientRequest);
        });
    }

    @Data
    public static class Config {
        private String validationUri = "http://ms-keycloak-adapter/roles";
        private String requiredRole = "admin";
        private int timeoutSeconds = 5;
        private int order = 1;
    }
}

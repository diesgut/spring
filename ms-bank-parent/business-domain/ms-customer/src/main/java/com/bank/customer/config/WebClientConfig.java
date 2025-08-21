package com.bank.customer.config;

import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;

import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import reactor.netty.http.client.HttpClient;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClientCustomizer webClientCustomizer() {
        HttpClient httpClient = HttpClient.create()
                // Connection Timeout: is a period within which a connection between a client and a server must be established
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
             /*   .option(ChannelOption.SO_KEEPALIVE, true)
                .option(EpollChannelOption.TCP_KEEPIDLE, 300)
                .option(EpollChannelOption.TCP_KEEPINTVL, 60)*/
                // Response Timeout: The maximum time we wait to receive a response after sending a request
                .responseTimeout(Duration.ofSeconds(1))
                // Read and Write Timeout: A read timeout occurs when no data was read within a certain
                // period of time, while the write timeout when a write operation cannot finish at a specific time
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                );

        return webClientBuilder -> webClientBuilder.clientConnector(new ReactorClientHttpConnector(httpClient));
    }
}

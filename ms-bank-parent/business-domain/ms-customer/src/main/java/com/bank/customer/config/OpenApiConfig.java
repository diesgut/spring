package com.bank.customer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AppInfoValues.class)
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI(AppInfoValues appInfoValues) {
        return new OpenAPI()
                .info(new Info()
                        .title(appInfoValues.name())
                        .version(appInfoValues.version())
                        .description(appInfoValues.description()));
    }
}

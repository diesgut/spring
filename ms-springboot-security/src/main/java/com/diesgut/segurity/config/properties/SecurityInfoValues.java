package com.diesgut.segurity.config.properties;

import jdk.jfr.Name;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.security")
public record SecurityInfoValues(@Name("api-header-name") String apiHeaderName, String secretKey) {
}
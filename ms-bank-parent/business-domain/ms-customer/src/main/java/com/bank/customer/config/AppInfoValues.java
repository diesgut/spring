package com.bank.customer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "info.app")
public record AppInfoValues(String name, String description, String version) {
}
package com.diesgut.segurity;

import com.diesgut.segurity.config.properties.SecurityInfoValues;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@EnableConfigurationProperties(SecurityInfoValues.class)
@SpringBootApplication
public class MsSpringbootSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsSpringbootSecurityApplication.class, args);
	}

}

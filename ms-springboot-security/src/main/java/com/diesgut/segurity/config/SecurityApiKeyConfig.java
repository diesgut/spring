package com.diesgut.segurity.config;

import com.diesgut.segurity.config.filter.ApiKeyAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@Profile("api-key-auth")
public class SecurityApiKeyConfig {

    private final ApiKeyAuthFilter apiKeyAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Deshabilitar CSRF (Cross-Site Request Forgery)
                // Es seguro para APIs stateless que no usan cookies/sesiones para autenticación.
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Definir la política de creación de sesiones como STATELESS
                // Spring Security no creará ni utilizará ninguna sesión HTTP.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 3. Añadir nuestro filtro personalizado ANTES del filtro de autenticación estándar
                // Esto asegura que nuestra lógica de API Key se ejecute primero.
                .addFilterBefore(apiKeyAuthFilter, UsernamePasswordAuthenticationFilter.class)

                // 4. Configurar las reglas de autorización para las peticiones HTTP
                .authorizeHttpRequests(auth -> auth
                        // Permite el acceso sin autenticación a endpoints de "salud" o documentación
                        .requestMatchers("/actuator/health", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        // Cualquier otra petición debe estar autenticada
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}

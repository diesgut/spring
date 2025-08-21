package com.diesgut.segurity.config;

import com.diesgut.segurity.config.filter.JwtTokenAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@Profile("token-auth")
public class SecurityTokenConfig {
    private final JwtTokenAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilitamos CSRF para APIs REST stateless
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Endpoints públicos
                        .anyRequest().authenticated() // Cualquier otra petición requiere autenticación
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sesiones stateless
                .authenticationProvider(authenticationProvider) // Proveedor de autenticación personalizado
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Añadimos nuestro filtro JWT

        return http.build();
    }
}

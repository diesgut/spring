package com.diesgut.segurity.config.filter;

import com.diesgut.segurity.config.properties.SecurityInfoValues;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {
    private final SecurityInfoValues securityInfoValues;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        // 1. Extraer la API Key del header
        String apiKey = request.getHeader(securityInfoValues.apiHeaderName());

        // 2. Validar la API Key
        if (securityInfoValues.secretKey().equals(apiKey)) {
            // 3. La clave es válida. Creamos un objeto de autenticación.
            // Este objeto representa al usuario/principal autenticado.
            // Para este caso simple, no necesitamos roles o permisos (authorities).

            List<GrantedAuthority> authorities = Collections.emptyList();
          //  List<GrantedAuthority> authorities = apiKeyService.getAuthoritiesForKey(apiKey);
            var authentication = new UsernamePasswordAuthenticationToken(
                    "api-client", // El "principal" o identificador del usuario
                    null,          // No se necesitan credenciales después de la autenticación
                    authorities // Sin roles/autoridades
            );

            // 4. Establecemos la autenticación en el contexto de seguridad de Spring.
            // A partir de aquí, Spring Security sabe que esta petición está autenticada.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 5. Continuamos con la cadena de filtros.
        // Si la clave no fue válida, el contexto de seguridad permanecerá vacío (anónimo).
        // La autorización posterior denegará el acceso si el endpoint lo requiere.
        filterChain.doFilter(request, response);
    }
}

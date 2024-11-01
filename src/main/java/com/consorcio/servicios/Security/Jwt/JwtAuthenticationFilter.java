package com.consorcio.servicios.Security.Jwt;

import com.consorcio.servicios.Security.Config.WebApiResponse;
import com.consorcio.servicios.Security.Enums.Role;
import com.consorcio.servicios.Security.Service.CustomUserDetailsService;
import com.consorcio.servicios.Security.Service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import java.io.IOException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import java.util.Collection;
import java.util.Set;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = getTokenFromRequest(request);
        String username;

        try {
            if (token != null) {
                username = jwtService.getUsernameFromToken(token);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Carga los detalles del usuario desde el servicio de UserDetails
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    if (jwtService.isTokenValid(token, userDetails)) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());

                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        String currentPath = request.getRequestURI();

                        if (!hasRequiredPermissionForPath(userDetails, currentPath)) {
                            // Crear la respuesta de error personalizada
                            WebApiResponse<Void> errorResponse = WebApiResponse.error("Acceso Denegado",
                                    "No tienes los permisos suficientes para acceder a esta funcionalidad.",
                                    HttpServletResponse.SC_UNAUTHORIZED);
                            // Convertir la respuesta a JSON
                            sendErrorResponse(response, errorResponse);
                            return;
                        }
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            }
        } catch (ExpiredJwtException ex) {
            WebApiResponse<Void> errorResponse = WebApiResponse.error("El token ha expirado",
                    "Por favor, inicia sesión nuevamente.", HttpServletResponse.SC_UNAUTHORIZED);
            sendErrorResponse(response, errorResponse);
            return;
        } catch (SignatureException ex) {
            WebApiResponse<Void> errorResponse = WebApiResponse.error("Firma del token no válida",
                    "Token corrupto o alterado.", HttpServletResponse.SC_UNAUTHORIZED);
            sendErrorResponse(response, errorResponse);
            return;
        } catch (MalformedJwtException ex) {
            WebApiResponse<Void> errorResponse = WebApiResponse.error("Token JWT mal formado",
                    "La solicitud contiene un token no válido.", HttpServletResponse.SC_BAD_REQUEST);
            sendErrorResponse(response, errorResponse);
            return;
        } catch (JwtException ex) {
            WebApiResponse<Void> errorResponse = WebApiResponse.error("Error en el token JWT", ex.getMessage(),
                    HttpServletResponse.SC_BAD_REQUEST);
            sendErrorResponse(response, errorResponse);
            return;
        }
        // Continúa con el siguiente filtro si todo está en orden
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    // Método auxiliar para enviar la respuesta de error en formato JSON
    private void sendErrorResponse(HttpServletResponse response, WebApiResponse<Void> errorResponse)
            throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String jsonResponse = objectMapper.writeValueAsString(errorResponse);

        response.setStatus(errorResponse.getStatusCode());
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
        response.getWriter().flush();
        response.getWriter().close();
    }

    private boolean hasRequiredPermissionForPath(UserDetails userDetails, String path) {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        // Verificar si el usuario tiene rol admin
        if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals(Role.ROLE_ADMIN.name()))) {
            return true;
        }

        // Obtener los permisos requeridos basados en la ruta
        Set<Role> requiredPermissions = getPermissionsForPath(path);

        // Si no se requiere permiso específico, no permitir el acceso
        if (requiredPermissions == null || requiredPermissions.isEmpty()) {
            return false;
        }

        // Verificar si el usuario tiene al menos uno de los permisos necesarios
        return authorities.stream()
                .anyMatch(authority -> requiredPermissions.stream()
                        .anyMatch(role -> authority.getAuthority().equals(role.name())));
    }

    // Método auxiliar para obtener los permisos requeridos basados en la ruta
    private Set<Role> getPermissionsForPath(String path) {
        if (path.startsWith("/api/v1/admin")) {
            return Set.of(Role.ROLE_ADMIN);
        } else if (path.startsWith("/api/v1/operator")) {
            return Set.of(Role.ROLE_OPERATOR, Role.ROLE_ADMIN);
        } else if (path.startsWith("/api/v1/user")) {
            return Set.of(Role.ROLE_USER, Role.ROLE_ADMIN);
        }
        // Si la ruta no coincide con ninguna, retornar un conjunto vacío
        return Set.of();
    }

}
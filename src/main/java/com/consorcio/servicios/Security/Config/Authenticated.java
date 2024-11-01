package com.consorcio.servicios.Security.Config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.consorcio.servicios.Security.Config.CustomUserDetails;

public class Authenticated {

    // Método para obtener el usuario autenticado
    public static CustomUserDetails getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            return (CustomUserDetails) auth.getPrincipal();
        } else {
            throw new RuntimeException("No se pudo autenticar al usuario.");
        }
    }

}
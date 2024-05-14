package com.consorcio.servicios.Security.Auth;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    
    String token;
    
}
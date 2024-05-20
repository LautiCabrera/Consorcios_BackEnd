package com.consorcio.servicios.Security.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    
    String username;
    String lastname;
    String firstname;
    String password;
    int dni;
    int phone;
    
}
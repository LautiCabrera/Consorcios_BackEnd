package com.consorcio.servicios.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    
    @NotBlank
    String username;
    @NotBlank
    String lastname;
    @NotBlank
    String firstname;
    @NotNull
    int dni;
    @NotNull
    int phone;
    
}
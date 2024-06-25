package com.consorcio.servicios.Security.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

    @NotBlank
    String username;
    @NotBlank
    String lastname;
    @NotBlank
    String firstname;
    @NotBlank
    String password;
    @NotNull
    int dni;
    @NotNull
    long phone;

}
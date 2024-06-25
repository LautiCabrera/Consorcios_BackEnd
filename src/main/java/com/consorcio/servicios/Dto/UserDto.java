package com.consorcio.servicios.Dto;

import com.consorcio.servicios.Enums.UserStatus;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    
    @NotBlank
    int id_user;
    @NotBlank
    String username;
    @NotBlank
    String lastname;
    @NotBlank
    String firstname;
    @NotNull
    int dni;
    @NotNull
    long phone;
    @NotNull
    UserStatus status;
    
}
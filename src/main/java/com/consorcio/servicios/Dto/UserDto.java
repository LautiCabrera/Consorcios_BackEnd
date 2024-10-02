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
    Long idUser;
    @NotBlank
    String username;
    @NotBlank
    String lastName;
    @NotBlank
    String firstName;
    @NotNull
    int dni;
    @NotNull
    String phone;
    @NotNull
    UserStatus status;

}
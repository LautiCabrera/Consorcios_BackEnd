package com.consorcio.servicios.Security.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto {

    @NotBlank
    String token;

}
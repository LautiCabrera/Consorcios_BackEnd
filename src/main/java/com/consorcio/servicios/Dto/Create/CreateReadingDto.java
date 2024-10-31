package com.consorcio.servicios.Dto.Create;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateReadingDto {

    @NotBlank
    private Double reading;

    @NotBlank
    private Long idPeriod;

    @NotBlank
    private Long idMeter;

}
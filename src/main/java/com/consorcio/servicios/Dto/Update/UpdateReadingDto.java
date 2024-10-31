package com.consorcio.servicios.Dto.Update;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateReadingDto {

    @NotBlank
    private Double reading;

    @NotBlank
    private Long idPeriod;

    @NotBlank
    private Long idMeter;

}
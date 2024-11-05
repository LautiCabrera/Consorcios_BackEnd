package com.consorcio.servicios.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadingDto {

    @NotBlank
    private Double reading;

    @NotBlank
    private Long idPeriod;

}

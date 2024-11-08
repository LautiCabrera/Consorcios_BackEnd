package com.consorcio.servicios.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeeDto {

    private Long idFee;
    @NotBlank
    private String name;
    @NotBlank
    private int price;
    @NotBlank
    private int consumptionMax;

}

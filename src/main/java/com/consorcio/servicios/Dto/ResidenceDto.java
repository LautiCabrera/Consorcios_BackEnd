package com.consorcio.servicios.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResidenceDto {

    private Long idUser;
    @NotBlank
    private String district;
    @NotBlank
    private String street;
    @NotBlank
    private Long number;
    @NotBlank
    private Long idLocation;
    @NotBlank
    private Long numberMeter;
    @NotBlank
    private Long idFee;

}
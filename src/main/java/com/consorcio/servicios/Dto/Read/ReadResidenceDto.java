package com.consorcio.servicios.Dto.Read;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadResidenceDto {

    private Long idUser;
    @NotBlank
    private Long idResidence;
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

    public ReadResidenceDto() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
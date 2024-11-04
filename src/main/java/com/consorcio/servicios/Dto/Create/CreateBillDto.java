package com.consorcio.servicios.Dto.Create;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateBillDto {
    
    @NotBlank
    private String user;
}

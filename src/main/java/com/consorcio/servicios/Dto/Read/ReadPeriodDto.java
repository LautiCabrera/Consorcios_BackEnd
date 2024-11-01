package com.consorcio.servicios.Dto.Read;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadPeriodDto {

    private Long idPeriod;
    private String name;
    private Long idModality;

}
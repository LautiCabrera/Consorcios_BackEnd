package com.consorcio.servicios.Dto.Read;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
@AllArgsConstructor
public class ReadReadingDto {

    private Long idReading;
    private Long idPeriod;
    private Double reading;
    private String period;
    private Date date;

}
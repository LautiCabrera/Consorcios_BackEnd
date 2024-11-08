package com.consorcio.servicios.Dto.Read;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConceptBillDto {

    private Double normalConsumption;
    private Long socialQuota;
    private Double surplus;
    private Long interests;
    private Long fines;
    private Long reconnection;
    private Long connection;
    private Long materials;
    private Long others;
    private Long discount;

}
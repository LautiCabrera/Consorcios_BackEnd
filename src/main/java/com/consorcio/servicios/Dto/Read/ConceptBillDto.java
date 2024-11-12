package com.consorcio.servicios.Dto.Read;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConceptBillDto {

    private Double normalConsumption;
    private Double socialQuota;
    private Double surplus;
    private Double interests;
    private Double fines;
    private Double reconnection;
    private Double connection;
    private Double materials;
    private Double others;
    private Double discount;

}
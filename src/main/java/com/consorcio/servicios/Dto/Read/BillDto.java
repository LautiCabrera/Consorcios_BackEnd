package com.consorcio.servicios.Dto.Read;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BillDto {

    private UserBillDto user;
    private ConceptBillDto concept;
    private DetailBillDto details;

}
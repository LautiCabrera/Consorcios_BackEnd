package com.consorcio.servicios.Dto.Read;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetailBillDto {

    private Long idBill;
    private Date dateBill;
    private String fee;
    private int consumptionMax;
    private int priceFee;
    private Double consumed;
    private Double surplus;
    private Double total;

}
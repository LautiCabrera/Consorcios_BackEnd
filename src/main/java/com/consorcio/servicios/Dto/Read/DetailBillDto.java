package com.consorcio.servicios.Dto.Read;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetailBillDto {

    private Long idBill;
    private Date dateBill;
    private Double total;

}
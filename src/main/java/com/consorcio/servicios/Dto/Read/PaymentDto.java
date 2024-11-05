package com.consorcio.servicios.Dto.Read;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentDto {

    private Long idBill;
    private Date dateBill;
    private Boolean paidStatus;
    private Double total;

}
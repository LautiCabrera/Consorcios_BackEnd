package com.consorcio.servicios.Dto.Read;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BillDto {
    
    String userName;
    String adress;
    String email;
    String location;
    String province;
    String country; 
    Date date_register;
    Long id_bill;
    Double normalConsumption;
    Long socialQuota;
    Double surplus;
    Long interests;
    Long fines;
    Long reconnection;
    Long connection;
    Long materials;
    Long others;
    Long discount;
    Double total;
}

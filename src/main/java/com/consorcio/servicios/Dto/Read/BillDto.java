package com.consorcio.servicios.Dto.Read;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BillDto {

    private String userFirstName;
    private String userLastName;
    private String address;
    private String email;
    private String location;
    private String province;
    private String country;
    private Date dateBill;
    private Long idBill;
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
    private Double total;
}

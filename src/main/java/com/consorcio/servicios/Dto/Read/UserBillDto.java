package com.consorcio.servicios.Dto.Read;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserBillDto {

    private String userFirstName;
    private String userLastName;
    private String address;
    private String email;
    private String location;

}
package com.consorcio.servicios.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBill;

    @Column(name = "id_meter")
    private Long idMeter;

    @Column(name = "id_reading")
    private Long idReading;

    @Column(name = "consumption")
    private Double consumption;

    @Column(name = "normal_consumption")
    private Double normalConsumption;

    @Column(name = "social_quota")
    private Double socialQuota;

    @Column(name = "surplus")
    private Double surplus;

    @Column(name = "surplus_price")
    private Double surplusPrice;

    @Column(name = "interests")
    private Double interests;

    @Column(name = "fines")
    private Double fines;

    @Column(name = "reconnection")
    private Double reconnection;

    @Column(name = "connection")
    private Double connection;

    @Column(name = "materials")
    private Double materials;

    @Column(name = "others")
    private Double others;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "total")
    private Double total;

    @Column(name = "paid_status")
    private Boolean paidStatus;

    @Column(name = "id_user_register")
    private Long idUserRegister;
    
    @Column(name = "id_user_update")
    private Long idUserUpdate;
    
    @Column(name = "date_register")
    private LocalDateTime dateRegister;

    @Column(name = "date_update")
    private LocalDateTime dateUpdate;

}

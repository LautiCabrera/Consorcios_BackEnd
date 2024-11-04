package com.consorcio.servicios.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    
    @Column(name ="id_reading")
    private Long idReading;
    
    @Column(name = "normal_consumption")
    private Double normalConsumption;
    
    @Column(name = "social_quota")
    private Long socialQuota;
    
    @Column(name = "surplus")
    private Double surplus;
    
    @Column(name = "interests")
    private Long interests; 
    
    @Column(name = "fines")
    private Long fines; 
    
    @Column(name = "reconnection")
    private Long reconnection; 
    
    @Column(name = "connection")
    private Long connection; 
    
    @Column(name = "materials")
    private Long materials;
    
    @Column(name = "others")
    private Long others;
    
    @Column(name = "discount")
    private Long discount; 
    
    @Column(name = "total")
    private Double total;
    
    @Column(name = "paid_status")
    private Boolean paidStatus; 
}

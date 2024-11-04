package com.consorcio.servicios.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "reading")
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReading;

    @Column(nullable = false, name = "reading")
    private Double reading;

    @Column(nullable = false, name = "date_reading")
    private LocalDateTime dateReading;

    @Column(nullable = false, name = "id_period")
    private Long idPeriod;

    @Column(nullable = false, name = "id_meter")
    private Long idMeter;

    @Column(name = "id_user_update")
    private Long idUserUpdate;

    @Column(name = "id_user_register")
    private Long idUserRegister;

    @Column(name = "date_update")
    private LocalDateTime dateUpdate;

    @Column(name = "date_register")
    private LocalDateTime datRegister;

}
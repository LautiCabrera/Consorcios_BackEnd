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

    @Column(nullable = false)
    private Double reading;

    @Column(nullable = false)
    private LocalDateTime dateReading;

    @Column(nullable = false)
    private Long idPeriod;

    @Column(nullable = false)
    private Long idMeter;

}
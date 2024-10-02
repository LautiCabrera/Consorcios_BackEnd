package com.consorcio.servicios.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "period")
public class Period {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPeriod;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long idModality;

}
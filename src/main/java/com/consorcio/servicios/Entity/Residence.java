package com.consorcio.servicios.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "residence")
public class Residence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResidence;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private Long number;

    @Column(nullable = false)
    private int postalCode;

    @Column(nullable = false)
    private float latitude;

    @Column(nullable = false)
    private float longitude;

    @Column(nullable = false)
    private Long idUser;

    @Column(nullable = false)
    private Long idMeter;

    @Column(nullable = false)
    private Long idLocation;

}
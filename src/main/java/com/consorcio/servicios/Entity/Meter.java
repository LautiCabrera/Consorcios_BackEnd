package com.consorcio.servicios.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "meter")
public class Meter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMeter;

    @Column(nullable = false, unique = true)
    private Long numberMeter;

    @Column(nullable = false)
    private Date dateConnection;

    @Column(nullable = false)
    private Long idFee;

}
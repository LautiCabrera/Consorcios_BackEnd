package com.consorcio.servicios.Entity;

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
    @GeneratedValue
    private int id_reading;

    @Column(nullable = false)
    private Double reading;

    @Column(nullable = false)
    private Double date_reading;

    @Column(nullable = false)
    private String period;

    @Column(nullable = false)
    private int year;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_modality", nullable = false, unique = true)
    private Modality modality;

    @OneToOne(mappedBy = "reading", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Meter meter;

}
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
    private long id_residence;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private long number;

    @Column(nullable = false)
    private int postal_code;

    @Column(nullable = false)
    private float latitude;

    @Column(nullable = false)
    private float longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_meter", nullable = true)
    private Meter meter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_location", nullable = false)
    private Location location;

}
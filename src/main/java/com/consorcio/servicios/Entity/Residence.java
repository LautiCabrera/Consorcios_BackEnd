package com.consorcio.servicios.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "residence")
public class Residence {

    @Id
    @GeneratedValue
    private int id_residence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_meter", nullable = false, unique = true)
    private Meter meter;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_location", nullable = false, unique = true)
    private Location location;

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

}
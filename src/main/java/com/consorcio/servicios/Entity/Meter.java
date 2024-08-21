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
    @GeneratedValue
    private int id_meter;

    @Column(nullable = false)
    private Long number_meter;

    @Column(nullable = false)
    private Date date_connection;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_fee", nullable = false, unique = true)
    private Fee fee;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_reading", nullable = false, unique = true)
    private Reading reading;

    @OneToOne(mappedBy = "meter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Residence residence;

}
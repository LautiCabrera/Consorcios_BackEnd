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
    private long id_meter;

    @Column(nullable = false, unique = true)
    private Long number_meter;

    @Column(nullable = false)
    private Date date_connection;

    @OneToOne(mappedBy = "meter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Residence residence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fee", nullable = false)
    private Fee fee;

    @OneToMany(mappedBy = "meter", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Reading> readings = new ArrayList<>();

}
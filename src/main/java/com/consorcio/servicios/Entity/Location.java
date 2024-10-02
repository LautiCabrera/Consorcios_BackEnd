package com.consorcio.servicios.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLocation;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long idProvince;

}
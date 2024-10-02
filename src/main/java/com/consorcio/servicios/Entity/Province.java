package com.consorcio.servicios.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "province")
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProvince;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long idCountry;

}
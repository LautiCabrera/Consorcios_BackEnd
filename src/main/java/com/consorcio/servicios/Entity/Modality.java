package com.consorcio.servicios.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "modality")
public class Modality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModality;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean active;

}
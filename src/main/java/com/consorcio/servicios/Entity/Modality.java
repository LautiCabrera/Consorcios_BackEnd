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
    @GeneratedValue
    private int id_modality;

    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy = "modality", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Reading reading;

}
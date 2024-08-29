package com.consorcio.servicios.Entity;

import java.util.List;
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
    private long id_modality;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "modality", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Period> periods;

    @Column(nullable = false)
    private boolean active;

}
package com.consorcio.servicios.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "fee")
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFee;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int consumptionMax;

    @Column(name = "id_user_update")
    private Long idUserUpdate;

    @Column(name = "id_user_register")
    private Long idUserRegister;

    @Column(name = "date_update")
    private LocalDateTime dateUpdate;

    @Column(name = "date_register")
    private LocalDateTime dateRegister;

}
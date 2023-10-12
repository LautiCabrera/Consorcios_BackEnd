package com.consorcios.gestion.Entity;

import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@Entity
@Access(AccessType.FIELD)
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario; 
    @NotNull
    private Integer id_consorcio;
    @NotNull
    private Integer id_tarifa;
    @NotNull
    private Integer num_conex;
    @NotNull
    private String nombre;
    @NotNull
    private Integer dni;
    @NotNull
    private String domicilio;
    private String email;
    private int telefono;

    public Usuario() {
        
    }

    public Usuario(int id_consorcio, int id_tarifa, int num_conex, String nombre, int dni, String domicilio, String email, int telefono) {
        this.id_consorcio = id_consorcio;
        this.id_tarifa = id_tarifa;
        this.num_conex = num_conex;
        this.nombre = nombre;
        this.dni = dni;
        this.domicilio = domicilio;
        this.email = email;
        this.telefono = telefono;
    }
    
}
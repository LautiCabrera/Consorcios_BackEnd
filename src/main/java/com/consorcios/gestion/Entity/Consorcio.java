package com.consorcios.gestion.Entity;

import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@Entity
@Access(AccessType.FIELD)
public class Consorcio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_consorcio; 
    @NotNull
    private Integer id_servicio;
    @NotNull
    private Integer cuit;
    @NotNull
    private String nombre;
    @NotNull
    private String localidad;
    @NotNull
    private String direccion;
    @NotNull
    private String cant_usuarios;

    public Consorcio() {
        
    }

    public Consorcio(Integer id_servicio, Integer cuit, String nombre, String localidad, String direccion, String cant_usuarios) {
        this.id_servicio = id_servicio;
        this.cuit = cuit;
        this.nombre = nombre;
        this.localidad = localidad;
        this.direccion = direccion;
        this.cant_usuarios = cant_usuarios;
    }
    
}

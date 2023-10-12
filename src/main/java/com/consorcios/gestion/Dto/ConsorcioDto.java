package com.consorcios.gestion.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
public class ConsorcioDto {
    
    @NotBlank
    private Integer id_servicio;
    @NotBlank
    private Integer cuit;
    @NotBlank
    private String nombre;
    @NotBlank
    private String localidad;
    @NotBlank
    private String direccion;
    @NotBlank
    private String cant_usuarios;

    public ConsorcioDto() {
        
    }

    public ConsorcioDto(Integer id_servicio, Integer cuit, String nombre, String localidad, String direccion, String cant_usuarios) {
        this.id_servicio = id_servicio;
        this.cuit = cuit;
        this.nombre = nombre;
        this.localidad = localidad;
        this.direccion = direccion;
        this.cant_usuarios = cant_usuarios;
    }
    
}

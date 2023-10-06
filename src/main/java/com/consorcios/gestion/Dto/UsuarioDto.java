package com.consorcios.gestion.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class UsuarioDto {
    
    @NotBlank
    private Integer id_consorcio;
    @NotBlank
    private Integer id_tarifa;
    @NotBlank
    private Integer num_conex;
    @NotBlank
    private String nombre;
    @NotBlank
    private Integer dni;
    @NotBlank
    private String domicilio;
    @NotBlank
    private String email;
    @NotBlank
    private int telefono;

    public UsuarioDto() {
    }

    public UsuarioDto(Integer id_consorcio, Integer id_tarifa, Integer num_conex, String nombre, Integer dni, String domicilio, String email, int telefono) {
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

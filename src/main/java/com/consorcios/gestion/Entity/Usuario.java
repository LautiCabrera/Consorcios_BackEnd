package com.consorcios.gestion.Entity;

import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;

@Entity
@Access(AccessType.FIELD)
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario; 
    private Integer id_consorcio;
    private Integer id_tarifa;
    private Integer num_conex;
    @NotNull
    private String nombre;
    @NotNull
    private Integer dni;
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

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_consorcio() {
        return id_consorcio;
    }

    public void setId_consorcio(Integer id_consorcio) {
        this.id_consorcio = id_consorcio;
    }

    public Integer getId_tarifa() {
        return id_tarifa;
    }

    public void setId_tarifa(Integer id_tarifa) {
        this.id_tarifa = id_tarifa;
    }

    public Integer getNum_conex() {
        return num_conex;
    }

    public void setNum_conex(Integer num_conex) {
        this.num_conex = num_conex;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    
    
    
}


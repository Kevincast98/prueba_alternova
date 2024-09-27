package com.ejemplo.plataforma_streaming.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private String nombre;
    @Basic
    private String apellido;
    @Basic
    private String tipodocumento;
    @Basic
    private int numerodocumento;
    @Basic
    private String email;
    @Basic
    private String password;

}

package com.ejemplo.plataforma_streaming.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int puntaje; // Entre 1 y 5

    @ManyToOne
    private PeliculaSerie peliculaSerie;

    @ManyToOne
    private Usuario usuario;

}

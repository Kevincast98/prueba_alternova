package com.ejemplo.plataforma_streaming.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class PeliculaSerie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String genero;
    private String tipo; // Película o serie

    @Column(nullable = true)  
    private Integer numVisualizaciones;

    @Column(nullable = true)
    private Double puntaje;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean vista = false;

    @OneToMany(mappedBy = "peliculaSerie")
    private List<Calificacion> calificaciones;
}



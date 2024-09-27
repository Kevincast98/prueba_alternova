package com.ejemplo.plataforma_streaming.repositories;

import com.ejemplo.plataforma_streaming.models.Calificacion;
import com.ejemplo.plataforma_streaming.models.PeliculaSerie;
import com.ejemplo.plataforma_streaming.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
    boolean existsByPeliculaSerieAndUsuario(PeliculaSerie peliculaSerie, Usuario usuario);
}

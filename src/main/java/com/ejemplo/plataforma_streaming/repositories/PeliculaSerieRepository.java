package com.ejemplo.plataforma_streaming.repositories;

import com.ejemplo.plataforma_streaming.models.PeliculaSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaSerieRepository extends JpaRepository<PeliculaSerie, Long> {
    List<PeliculaSerie> findAllByOrderByNombreAsc();
    List<PeliculaSerie> findAllByOrderByTipoAsc();
    List<PeliculaSerie> findAllByOrderByGeneroAsc();
    List<PeliculaSerie> findAllByOrderByPuntajeAsc();

    List<PeliculaSerie> findByNombreContaining(String nombre);
    List<PeliculaSerie> findByTipo(String tipo);
    List<PeliculaSerie> findByGenero(String genero);
}


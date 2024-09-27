package com.ejemplo.plataforma_streaming.services;

import com.ejemplo.plataforma_streaming.models.PeliculaSerie;
import com.ejemplo.plataforma_streaming.repositories.PeliculaSerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PeliculaSerieService {

    @Autowired
    private PeliculaSerieRepository repository;

    // a. Obtener una película o serie aleatoria
    public PeliculaSerie obtenerAleatoria() {
        List<PeliculaSerie> todas = repository.findAll();
        if (!todas.isEmpty()) {
            Random rand = new Random();
            return todas.get(rand.nextInt(todas.size()));
        }
        return null;
    }

    // b. Obtener todas las películas o series ordenadas por (nombre, tipo, género, puntaje)
    public List<PeliculaSerie> obtenerTodasOrdenadas(String ordenarPor) {
        switch (ordenarPor.toLowerCase()) {
            case "nombre":
                return repository.findAllByOrderByNombreAsc();
            case "tipo":
                return repository.findAllByOrderByTipoAsc();
            case "genero":
                return repository.findAllByOrderByGeneroAsc();
            case "puntaje":
                return repository.findAllByOrderByPuntajeAsc();
            default:
                return repository.findAllByOrderByNombreAsc();
        }
    }

    // c. Filtros por nombre, tipo, genero
    public List<PeliculaSerie> filtrar(String nombre, String tipo, String genero) {
        if (nombre != null && !nombre.isEmpty()) {
            return repository.findByNombreContaining(nombre);
        } else if (tipo != null && !tipo.isEmpty()) {
            return repository.findByTipo(tipo);
        } else if (genero != null && !genero.isEmpty()) {
            return repository.findByGenero(genero);
        } else {
            return repository.findAll();
        }
    }

    // d. Marcar como vista
    public PeliculaSerie marcarComoVista(Long id) {
        Optional<PeliculaSerie> peliculaSerieOptional = repository.findById(id);
        if (peliculaSerieOptional.isPresent()) {
            PeliculaSerie peliculaSerie = peliculaSerieOptional.get();
            peliculaSerie.setNumVisualizaciones(peliculaSerie.getNumVisualizaciones() + 1);
            peliculaSerie.setVista(true);
            return repository.save(peliculaSerie);
        }
        return null;
    }



    // e. Puntuar una película o serie
    public PeliculaSerie puntuarPeliculaSerie(Long id, int puntaje) {
        if (puntaje < 1 || puntaje > 5) {
            throw new IllegalArgumentException("El puntaje debe estar entre 1 y 5");
        }
        Optional<PeliculaSerie> peliculaSerieOptional = repository.findById(id);
        if (peliculaSerieOptional.isPresent()) {
            PeliculaSerie peliculaSerie = peliculaSerieOptional.get();
            // Lógica para ajustar el puntaje
            peliculaSerie.setPuntaje((double) puntaje);
            return repository.save(peliculaSerie);
        }
        return null;
    }

}

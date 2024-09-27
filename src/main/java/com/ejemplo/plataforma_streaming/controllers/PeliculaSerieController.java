package com.ejemplo.plataforma_streaming.controllers;

import com.ejemplo.plataforma_streaming.models.PeliculaSerie;
//import com.ejemplo.plataforma_streaming.services.JwtService;
import com.ejemplo.plataforma_streaming.services.PeliculaSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.jsonwebtoken.Claims;


@RestController
@RequestMapping("/api/peliculas-series")
public class PeliculaSerieController {

    @Autowired
    private PeliculaSerieService servicio;


    // a. Obtener una película o serie aleatoria
    @GetMapping("/aleatoria")
    public ResponseEntity<Map<String, Object>> obtenerAleatoria(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        PeliculaSerie peliculaSerie = servicio.obtenerAleatoria();

        // Crear un LinkedHashMap para incluir el success y el resultado
        Map<String, Object> response = new LinkedHashMap<>();

        if (peliculaSerie != null) {
            response.put("success", true);
            response.put("data", peliculaSerie);
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("data", null);
            return ResponseEntity.noContent().build();
        }
    }

    // b. Obtener todas las películas o series ordenadas por (nombre, tipo, género, puntaje)
    @GetMapping
    public ResponseEntity<Map<String, Object>> obtenerTodasOrdenadas(
            @RequestParam(required = false, defaultValue = "nombre") String ordenarPor) {
        List<PeliculaSerie> peliculasSeries = servicio.obtenerTodasOrdenadas(ordenarPor);

        // Crear un LinkedHashMap para incluir el success y el resultado
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("success", true);
        response.put("data", peliculasSeries);

        return ResponseEntity.ok(response);
    }


    // c. Filtros por nombre, tipo, genero
    @GetMapping("/filtro")
    public ResponseEntity<Map<String, Object>> filtrar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String genero) {

        List<PeliculaSerie> peliculasSeries = servicio.filtrar(nombre, tipo, genero);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("success", true);
        response.put("data", peliculasSeries);

        return ResponseEntity.ok(response);
    }


    // d. Marcar una película o serie como vista
    @PostMapping("/{id}/vista")
    public ResponseEntity<Map<String, Object>> marcarComoVista(@PathVariable Long id) {
        PeliculaSerie peliculaSerie = servicio.marcarComoVista(id);

        // Crear un mapa para incluir el resultado y el success
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("success", true);
        response.put("message", "La película o serie ha sido marcada como vista.");
        response.put("data", peliculaSerie);

        return ResponseEntity.ok(response);
    }



    // e. Puntuar una película o serie
    @PostMapping("/{id}/puntuar")
    public ResponseEntity<Map<String, Object>> puntuarPeliculaSerie(@PathVariable Long id, @RequestParam int puntaje) {
        PeliculaSerie peliculaSerie = servicio.puntuarPeliculaSerie(id, puntaje);

        // Crear un mapa para incluir el resultado y el success
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("success", true);
        response.put("message", "La película o serie ha sido puntuada.");
        response.put("data", peliculaSerie);

        return ResponseEntity.ok(response);
    }

}

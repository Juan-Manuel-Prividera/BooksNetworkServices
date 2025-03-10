package com.app.lectorservice.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.app.lectorservice.models.dtos.SesionDeLecturaDTO;
import com.app.lectorservice.models.entities.lectores.Lector;
import com.app.lectorservice.models.entities.lecturas.EstadoLectura;
import com.app.lectorservice.models.entities.lecturas.Lectura;
import com.app.lectorservice.models.entities.lecturas.SesionDeLectura;
import com.app.lectorservice.services.LectorService;
import com.app.lectorservice.services.LibroService;

@RestController
@RequestMapping("/lecturas")
public class LecturaController {
  private final LectorService lectorService;
  private final LibroService libroService;

  public LecturaController(LectorService lectorService, LibroService libroService) {
    this.lectorService = lectorService;
    this.libroService = libroService;
  }

  @GetMapping("/{lectorId}/lecturas")
  public ResponseEntity<List<Lectura>> getLecturas(@PathVariable Long lectorId) {
    try {
      List<Lectura> lecturas = lectorService.getLecturas(lectorId);
      return ResponseEntity.ok(lecturas);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  } 

  @GetMapping("/{lecturaId}")
  public ResponseEntity<Lectura> getLectura(@PathVariable Long lecturaId) {
    try {
      Lectura lectura = lectorService.getLectura(lecturaId);
      return ResponseEntity.ok(lectura);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  } 

  @GetMapping("/{lecturaId}/sesiones")
  public ResponseEntity<List<SesionDeLectura>> getSesionesDeLectura(@PathVariable Long lecturaId) {
    try {
      List<SesionDeLectura> sesiones = lectorService.getSesionesDeLectura(lecturaId);
      return ResponseEntity.ok(sesiones);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  } 

  @GetMapping("/sesiones/{sesionId}")
  public ResponseEntity<SesionDeLectura> getSesionDeLectura(@PathVariable Long sesionId) {
    try {
      SesionDeLectura sesion = lectorService.getSesionDeLectura(sesionId);
      return ResponseEntity.ok(sesion);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  } 
  
  @PostMapping("/{lectorId}/libro/{libroId}")
  public ResponseEntity<Lectura> crearLectura(@PathVariable Long lectorId, @PathVariable Long libroId) {
    try {
      Lector lector = lectorService.getLector(lectorId);
  
    Lectura lectura = Lectura.builder()
      .lector(lector)
      .libroId(libroId)
      .fechaInicio(LocalDateTime.now())
      .estado(EstadoLectura.PENDIENTE)
      .progreso(0.0)
      .build();

      lectorService.guardarLectura(lectura);
      return ResponseEntity.ok(lectura);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping("/sesiones/{lecturaId}")
  public ResponseEntity<SesionDeLectura> crearSesionDeLectura(@PathVariable Long lecturaId, @RequestBody SesionDeLecturaDTO sesionDeLecturaDTO) {
    try {
      Lectura lectura = lectorService.getLectura(lecturaId);
      SesionDeLectura sesionDeLectura = SesionDeLectura.builder()
        .lectura(lectura)
      .fechaInicio(sesionDeLecturaDTO.getFecha())
      .paginaInicial(sesionDeLecturaDTO.getPagina())
      .build();
    
      lectura.setEstado(EstadoLectura.EN_PROGRESO);

      lectorService.actualizarLectura(lectura);
      lectorService.guardarSesionDeLectura(sesionDeLectura);
      return ResponseEntity.ok(sesionDeLectura);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PutMapping("/sesiones/{sesionId}")
  public ResponseEntity<SesionDeLectura> actualizarSesionDeLectura(@PathVariable Long sesionId, @RequestBody SesionDeLecturaDTO sesionDeLecturaDTO) {
    try {
      SesionDeLectura sesionDeLectura = lectorService.getSesionDeLectura(sesionId);
      sesionDeLectura.setPaginaFinal(sesionDeLecturaDTO.getPagina());
      sesionDeLectura.setFechaFin(sesionDeLecturaDTO.getFecha());
      sesionDeLectura.calcularDuracion();
      
      Lectura lectura = sesionDeLectura.getLectura();
      int paginasTotales = libroService.getCantidadPaginas(lectura.getLibroId());
      lectura.setProgreso((double) sesionDeLectura.getPaginaFinal() / paginasTotales * 100);
      if (lectura.getProgreso() == 100) {
        lectura.setEstado(EstadoLectura.LEIDO);
      }

      lectorService.actualizarLectura(lectura);
      lectorService.actualizarSesionDeLectura(sesionDeLectura);
      return ResponseEntity.ok(sesionDeLectura);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }
}

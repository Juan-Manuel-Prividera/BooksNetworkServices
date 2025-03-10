package com.app.lectorservice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;

import com.app.lectorservice.models.dtos.LectorDTO;
import com.app.lectorservice.models.entities.lectores.Lector;
import com.app.lectorservice.models.entities.lectores.TipoPerfil;
import com.app.lectorservice.services.LectorService;

@RestController
@RequestMapping("/lectores")
public class LectorController {
  private final LectorService lectorService;

  public LectorController(LectorService lectorService) {
    this.lectorService = lectorService;
  }

  @GetMapping("/{lectorId}")
  public ResponseEntity<Lector> getLector(@PathVariable Long lectorId) {
    try {
      Lector lector = lectorService.getLector(lectorId);
      return ResponseEntity.ok(lector);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping("/")
  public ResponseEntity<Lector> crearLector(@RequestBody LectorDTO lectorDTO) {
    try {
      Lector lector = Lector.builder()
        .usuarioId(lectorDTO.getUsuarioId())
        .nombre(lectorDTO.getNombre())
        .apellido(lectorDTO.getApellido())
        .email(lectorDTO.getEmail())
        .pais(lectorDTO.getPais())
        .fechaNacimiento(lectorDTO.getFechaNacimiento())
        .activo(true)
        .fechaCreacion(LocalDateTime.now())
        .fechaActualizacion(LocalDateTime.now())
        .descripcion(lectorDTO.getDescripcion())
        .imagenPerfil(lectorDTO.getImagenPerfil())
        .tipoPerfil(TipoPerfil.valueOf(lectorDTO.getTipoPerfil()))
        .build();

      lectorService.guardarLector(lector);
      return ResponseEntity.ok(lector);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PutMapping("/{lectorId}")
  public ResponseEntity<Lector> actualizarLector(@PathVariable Long lectorId, @RequestBody LectorDTO lectorDTO) {
    try {
      Lector lector = lectorService.getLector(lectorId);
      lector.setNombre(lectorDTO.getNombre());
      lector.setApellido(lectorDTO.getApellido());
      lector.setEmail(lectorDTO.getEmail());
      lector.setPais(lectorDTO.getPais());
      lector.setFechaNacimiento(lectorDTO.getFechaNacimiento());
      lector.setFechaActualizacion(LocalDateTime.now());
      lector.setDescripcion(lectorDTO.getDescripcion());
      lector.setImagenPerfil(lectorDTO.getImagenPerfil());
      lector.setTipoPerfil(TipoPerfil.valueOf(lectorDTO.getTipoPerfil()));

      lectorService.actualizarLector(lector);
      return ResponseEntity.ok(lector);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @DeleteMapping("/{lectorId}")
  public ResponseEntity<Void> eliminarLector(@PathVariable Long lectorId) {
    try {
      lectorService.eliminarLector(lectorId);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }   
  
  
}

package com.app.socialservice.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.socialservice.models.dtos.PublicacionDTO;
import com.app.socialservice.models.entities.Publicacion;
import com.app.socialservice.services.PublicacionService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {
  private final PublicacionService publicacionService;

  public PublicacionController(PublicacionService publicacionService) {
    this.publicacionService = publicacionService;
  }

  @PostMapping("/")
  public ResponseEntity<PublicacionDTO> crearPublicacion(@RequestBody PublicacionDTO publicacionDTO) {
    try {
    Publicacion publicacion = Publicacion.builder()
      .contenidoText(publicacionDTO.getContenidoText())
      .mediaUrl(publicacionDTO.getMediaUrl())
      .fechaPublicacion(LocalDateTime.now())
      .lectorPublicadorId(publicacionDTO.getLectorPublicadorId())
      .nombrePublicador(publicacionDTO.getNombrePublicador())
      .imagenPublicador(publicacionDTO.getImagenPublicador())
      .build();

    publicacionService.guardarPublicacion(publicacion);

    return ResponseEntity.ok(publicacionDTO);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping("/{lectorId}")
  public ResponseEntity<List<Publicacion>> obtenerPublicacionesPorLector(@PathVariable String lectorId) {
    try {
      List<Publicacion> publicaciones = publicacionService.obtenerPublicacionesPorLector(lectorId);
      return ResponseEntity.ok(publicaciones);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<PublicacionDTO> actualizarPublicacion(@PathVariable String id, @RequestBody PublicacionDTO publicacionDTO) {
    try {
      Publicacion publicacion = publicacionService.obtenerPublicacionPorId(id);
      if (publicacion == null) {
        return ResponseEntity.notFound().build();
      }
      publicacion.setContenidoText(publicacionDTO.getContenidoText());
      publicacion.setMediaUrl(publicacionDTO.getMediaUrl());
    
      publicacionService.guardarPublicacion(publicacion);
      return ResponseEntity.ok(publicacionDTO);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  } 

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarPublicacion(@PathVariable String id) {
    try {
      publicacionService.eliminarPublicacion(id);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  } 
}

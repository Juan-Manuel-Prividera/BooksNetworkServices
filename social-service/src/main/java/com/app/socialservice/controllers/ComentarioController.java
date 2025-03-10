package com.app.socialservice.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.socialservice.models.dtos.ComentarioDTO;
import com.app.socialservice.models.entities.Comentario;
import com.app.socialservice.models.entities.Publicacion;
import com.app.socialservice.services.ComentarioService;
import com.app.socialservice.services.PublicacionService;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {
  private final ComentarioService comentarioService;
  private final PublicacionService publicacionService;

  public ComentarioController(ComentarioService comentarioService, PublicacionService publicacionService) {
    this.comentarioService = comentarioService;
    this.publicacionService = publicacionService;
  }

  @PostMapping("/")
  public ResponseEntity<Comentario> crearComentario(@RequestBody ComentarioDTO comentarioDTO) {
    try {
      Publicacion publicacion = publicacionService.obtenerPublicacionPorId(comentarioDTO.getPublicacionId());
      Comentario comentarioPadre = null;
      if (comentarioDTO.getComentarioPadreId() != null) 
        comentarioPadre = comentarioService.obtenerComentarioPorId(comentarioDTO.getComentarioPadreId());
      
      Comentario comentario = Comentario.builder()
        .lectorId(comentarioDTO.getLectorId())
        .contenido(comentarioDTO.getContenido())
        .activo(true)
        .fechaComentario(LocalDateTime.now())
        .publicacion(publicacion) 
        .comentarioPadre(comentarioPadre)
        .build();

      comentarioService.guardarComentario(comentario);
      return ResponseEntity.ok(comentario);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarComentario(@PathVariable String id) {
    try {
      comentarioService.eliminarComentario(id);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    } 
  }

  @PutMapping("/{id}")
  public ResponseEntity<Comentario> actualizarComentario(@PathVariable String id, @RequestBody ComentarioDTO comentarioDTO) {
    try {
      Comentario comentario = comentarioService.obtenerComentarioPorId(id);
      if (comentario == null) {
        return ResponseEntity.notFound().build();
      }
      comentario.setContenido(comentarioDTO.getContenido());
      comentarioService.guardarComentario(comentario);
      return ResponseEntity.ok(comentario);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    } 
  }

  @GetMapping("/{id}")
  public ResponseEntity<Comentario> obtenerComentario(@PathVariable String id) {
    try {
      Comentario comentario = comentarioService.obtenerComentarioPorId(id);
      return ResponseEntity.ok(comentario);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  } 

  @GetMapping("/publicacion/{publicacionId}")
  public ResponseEntity<List<Comentario>> obtenerComentariosPorPublicacion(@PathVariable String publicacionId) {
    try {
      List<Comentario> comentarios = comentarioService.obtenerComentariosPorPublicacion(publicacionId);
      return ResponseEntity.ok(comentarios);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  } 

  @GetMapping("/lector/{lectorId}")
  public ResponseEntity<List<Comentario>> obtenerComentariosPorLector(@PathVariable String lectorId) {
    try {
      List<Comentario> comentarios = comentarioService.obtenerComentariosPorLector(lectorId);
      return ResponseEntity.ok(comentarios);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }     
}

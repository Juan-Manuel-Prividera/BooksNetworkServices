package com.app.socialservice.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.socialservice.models.dtos.LikeDTO;
import com.app.socialservice.models.entities.Comentario;
import com.app.socialservice.models.entities.Like;
import com.app.socialservice.models.entities.Publicacion;
import com.app.socialservice.services.ComentarioService;
import com.app.socialservice.services.LikeService;
import com.app.socialservice.services.PublicacionService;

@RestController
@RequestMapping("/likes")
public class LikeController {
  private final LikeService likeService;
  private final PublicacionService publicacionService;
  private final ComentarioService comentarioService;

  public LikeController(LikeService likeService, PublicacionService publicacionService, ComentarioService comentarioService) {
      this.likeService = likeService;
      this.publicacionService = publicacionService;
      this.comentarioService = comentarioService;
  }
  
  @PostMapping("/")
  public ResponseEntity<Like> crearLike(@RequestBody LikeDTO likeDTO) {
    try {
      Publicacion publicacion = publicacionService.obtenerPublicacionPorId(likeDTO.getPublicacionId());
      Comentario comentario = comentarioService.obtenerComentarioPorId(likeDTO.getComentarioId());
      Like like = Like.builder()
        .lectorId(likeDTO.getLectorId())
        .publicacion(publicacion)
        .comentario(comentario)
        .activo(true)
        .fechaLike(LocalDateTime.now())
        .build();
      likeService.guardarLike(like);
      return ResponseEntity.ok(like);
    
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarLike(@PathVariable String id) {
    try {
      likeService.eliminarLike(id);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping("/publicacion/{publicacionId}")
  public ResponseEntity<List<Like>> obtenerLikesPorPublicacion(@PathVariable String publicacionId) {
    try {
      List<Like> likes = likeService.obtenerLikesPorPublicacion(publicacionId);
      return ResponseEntity.ok(likes);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  } 

  @GetMapping("/comentario/{comentarioId}")
  public ResponseEntity<List<Like>> obtenerLikesPorComentario(@PathVariable String comentarioId) {
    try {
      List<Like> likes = likeService.obtenerLikesPorComentario(comentarioId);
      return ResponseEntity.ok(likes);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }   
}

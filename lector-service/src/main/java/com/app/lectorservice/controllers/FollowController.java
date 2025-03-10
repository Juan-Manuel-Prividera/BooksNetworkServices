package com.app.lectorservice.controllers;

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

import com.app.lectorservice.models.dtos.FollowDTO;
import com.app.lectorservice.models.entities.lectores.Follow;
import com.app.lectorservice.models.entities.lectores.Lector;
import com.app.lectorservice.services.FollowService;
import com.app.lectorservice.services.LectorService;

@RestController
@RequestMapping("/follow")
public class FollowController {
  private final FollowService followService;
  private final LectorService lectorService;

  public FollowController(FollowService followService, LectorService lectorService) {
    this.followService = followService;
    this.lectorService = lectorService;
  }

  @PostMapping("/")
  public ResponseEntity<Follow> crearFollow(@RequestBody FollowDTO followDTO) {
    Lector lectorOrigen = lectorService.getLector(followDTO.getLectorOrigen());
    Lector lectorDestino = lectorService.getLector(followDTO.getLectorDestino());
    try {
      Follow follow = Follow.builder()
        .origen(lectorOrigen)
        .destino(lectorDestino)
        .fechaSolicitud(LocalDateTime.now())
        .activo(true)
        .build();

      followService.guardarFollow(follow);
      // TODO: Enviar notificación al lector destino 

      return ResponseEntity.ok(follow);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @DeleteMapping("/{followId}")
  public ResponseEntity<Void> cancelarFollow(@PathVariable Long followId) {
    try {
      followService.cancelarFollow(followId);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping("seguidores/{lectorId}")
  public ResponseEntity<List<Lector>> getSeguidores(@PathVariable Long lectorId) {
    List<Lector> seguidores = followService.getSeguidores(lectorId);
    return ResponseEntity.ok(seguidores);
  }

  @GetMapping("seguidos/{lectorId}")
  public ResponseEntity<List<Lector>> getSeguidos(@PathVariable Long lectorId) {
    List<Lector> seguidos = followService.getSeguidos(lectorId);
    return ResponseEntity.ok(seguidos);
  }
}



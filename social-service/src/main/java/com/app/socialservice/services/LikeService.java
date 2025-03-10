package com.app.socialservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.socialservice.models.entities.Like;
import com.app.socialservice.models.repositories.LikeRepository;

@Service
public class LikeService {
  private final LikeRepository likeRepository;

  public LikeService(LikeRepository likeRepository) {
    this.likeRepository = likeRepository;
  }

  public void guardarLike(Like like) {
    likeRepository.save(like);
  }

  public void eliminarLike(String id) {
    Like like = likeRepository.findById(id).orElse(null);
    if (like != null) {
      like.setActivo(false);
      guardarLike(like);
    }
  }

  public List<Like> obtenerLikesPorComentario(String comentarioId) {
    return likeRepository.findByComentarioId(comentarioId);
  }

  public List<Like> obtenerLikesPorPublicacion(String publicacionId) {
    return likeRepository.findByPublicacionId(publicacionId);
  }
  
}

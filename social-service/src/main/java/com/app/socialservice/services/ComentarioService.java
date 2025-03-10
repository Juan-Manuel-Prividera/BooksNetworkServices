package com.app.socialservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.socialservice.models.entities.Comentario;
import com.app.socialservice.models.entities.Like;
import com.app.socialservice.models.repositories.ComentarioRepository;
import com.app.socialservice.models.repositories.LikeRepository;

@Service
public class ComentarioService {
  private final ComentarioRepository comentarioRepository;
  private final LikeRepository likeRepository;    

  public ComentarioService(ComentarioRepository comentarioRepository, LikeRepository likeRepository) {
    this.comentarioRepository = comentarioRepository;
    this.likeRepository = likeRepository;
  }

  public void guardarComentario(Comentario comentario) {
    comentarioRepository.save(comentario);
  }

  public Comentario obtenerComentarioPorId(String id) {
      return comentarioRepository.findById(id).orElse(null);
  }

  public void eliminarComentario(String id) {
      Comentario comentario = obtenerComentarioPorId(id);
      if (comentario != null) {
        comentario.setActivo(false);
        List<Comentario> comentariosHijos = comentarioRepository.findByComentarioPadreId(id);
        // TODO: Cuidado con esto
        for (Comentario comentarioHijo : comentariosHijos) {
          if (comentarioHijo.getActivo()) {
            eliminarComentario(comentarioHijo.getId());
          }
        }
  
        List<Like> likes = likeRepository.findByComentarioId(id);
      for (Like like : likes) {
        like.setActivo(false);
        likeRepository.save(like);
      }

      guardarComentario(comentario);
    }
    // TODO: Si no existe loggear error
  }

  public List<Comentario> obtenerComentariosPorPublicacion(String publicacionId) {
    return comentarioRepository.findByPublicacionId(publicacionId);
  }

  public List<Comentario> obtenerComentariosPorLector(String lectorId) {
    return comentarioRepository.findByLectorId(lectorId);
  }
  
}

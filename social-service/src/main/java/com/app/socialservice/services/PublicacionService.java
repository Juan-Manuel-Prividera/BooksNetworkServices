package com.app.socialservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.socialservice.models.entities.Comentario;
import com.app.socialservice.models.entities.Like;
import com.app.socialservice.models.entities.Publicacion;
import com.app.socialservice.models.repositories.ComentarioRepository;
import com.app.socialservice.models.repositories.LikeRepository;
import com.app.socialservice.models.repositories.PublicacionRepository;

@Service
public class PublicacionService {
  private final PublicacionRepository publicacionRepository;
  private final ComentarioRepository comentarioRepository;
  private final LikeRepository likeRepository;

  public PublicacionService(PublicacionRepository publicacionRepository, ComentarioRepository comentarioRepository, LikeRepository likeRepository) {
    this.publicacionRepository = publicacionRepository;
    this.comentarioRepository = comentarioRepository;
    this.likeRepository = likeRepository;
  }

  public void guardarPublicacion(Publicacion publicacion) {
    publicacionRepository.save(publicacion);
  }


  public List<Publicacion> obtenerPublicacionesPorLector(String lectorPublicadorId) {
    return publicacionRepository.findByLectorPublicadorId(lectorPublicadorId);
  }

  public Publicacion obtenerPublicacionPorId(String id) {
    return publicacionRepository.findById(id).orElse(null);
  }

  public void eliminarPublicacion(String id) {
    Publicacion publicacion = obtenerPublicacionPorId(id);
    if (publicacion != null) {
      publicacion.setActivo(false);

      List<Comentario> comentarios = comentarioRepository.findByPublicacionId(id);
      for (Comentario comentario : comentarios) {
        comentario.setActivo(false);
        comentarioRepository.save(comentario);
      }

      List<Like> likes = likeRepository.findByPublicacionId(id);
      for (Like like : likes) {
        like.setActivo(false);
        likeRepository.save(like);
      }

      guardarPublicacion(publicacion);
    }
  }
  
}

package com.app.socialservice.models.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.socialservice.models.entities.Comentario;

@Repository
public interface ComentarioRepository extends MongoRepository<Comentario, String>{

  List<Comentario> findByPublicacionId(String publicacionId);

  List<Comentario> findByLectorId(String lectorId);

  List<Comentario> findByComentarioPadreId(String id);
  
}

package com.app.socialservice.models.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.app.socialservice.models.entities.Like;

@Repository
public interface LikeRepository extends MongoRepository<Like, String>{

  List<Like> findByPublicacionId(String id);

  List<Like> findByComentarioId(String comentarioId);
  
}

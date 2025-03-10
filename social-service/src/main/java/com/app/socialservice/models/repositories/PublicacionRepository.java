package com.app.socialservice.models.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.socialservice.models.entities.Publicacion;

@Repository
public interface PublicacionRepository extends MongoRepository<Publicacion, String>{

  List<Publicacion> findByLectorPublicadorId(String lectorPublicadorId);
  
}

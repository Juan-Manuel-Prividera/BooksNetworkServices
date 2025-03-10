package com.app.librosservice.models.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.librosservice.models.entities.Imagen;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Long> {
  Optional<Imagen> findBySmallThumbnail(String smallThumbnail);
}

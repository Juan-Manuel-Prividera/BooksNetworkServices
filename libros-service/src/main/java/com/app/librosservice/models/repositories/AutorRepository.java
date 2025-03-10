package com.app.librosservice.models.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.librosservice.models.entities.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
  Optional<Autor> findByNombreCompleto(String nombreCompleto);
}

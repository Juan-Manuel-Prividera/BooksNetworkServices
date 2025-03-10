package com.app.librosservice.models.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.librosservice.models.entities.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
  Optional<Categoria> findByNombreCategoria(String nombreCategoria);
}
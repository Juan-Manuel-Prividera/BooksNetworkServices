package com.app.librosservice.models.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.librosservice.models.entities.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {
  Optional<List<Libro>> findByTitulo(String titulo);
  @Query("SELECT l FROM Libro l JOIN l.autores a WHERE a.nombreCompleto = :autor")
  Optional<List<Libro>> findByAutor(String autor);
  @Query("SELECT l FROM Libro l JOIN l.categorias c WHERE c.nombreCategoria = :categoria")
  Optional<List<Libro>> findByCategoria(String categoria);
  Optional<Libro> findByVolumeId(String volumeId);
  
  @Query("SELECT l FROM Libro l JOIN l.isbn i WHERE i.isbn = :isbn")
  Optional<List<Libro>> findByIsbn(String isbn);
}

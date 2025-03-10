package com.app.librosservice.models.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.librosservice.models.entities.Isbn;

@Repository
public interface IsbnRepository extends JpaRepository<Isbn, Long> {
  Optional<Isbn> findByIsbn(String identifier);
}
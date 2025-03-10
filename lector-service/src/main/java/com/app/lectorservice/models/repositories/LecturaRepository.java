package com.app.lectorservice.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.lectorservice.models.entities.lecturas.Lectura;

@Repository
public interface LecturaRepository extends JpaRepository<Lectura, Long> {

  List<Lectura> findByLectorId(Long lectorId);
  
}

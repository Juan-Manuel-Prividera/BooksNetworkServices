package com.app.lectorservice.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.lectorservice.models.entities.lecturas.SesionDeLectura;

@Repository
public interface SesionDeLecturaRepository extends JpaRepository<SesionDeLectura, Long> {

  List<SesionDeLectura> findByLecturaId(Long lecturaId);
  
}

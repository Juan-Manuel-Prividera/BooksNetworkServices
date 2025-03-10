package com.app.lectorservice.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.lectorservice.models.entities.lectores.Lector;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {
  
}

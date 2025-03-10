package com.app.lectorservice.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.lectorservice.models.entities.lectores.Follow;
import com.app.lectorservice.models.entities.lectores.Lector;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

  List<Lector> findByDestinoId(Long lectorId);

  List<Lector> findByOrigenId(Long lectorId);
  
}

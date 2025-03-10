package com.app.usersservice.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.usersservice.models.entities.usuarios.Rol;
import com.app.usersservice.models.entities.usuarios.RolType;

public interface RolRepository extends JpaRepository<Rol, Long> {
  Rol findByRolType(RolType rolType);
}

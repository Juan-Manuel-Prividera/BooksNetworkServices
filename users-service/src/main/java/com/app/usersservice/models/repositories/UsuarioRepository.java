package com.app.usersservice.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.usersservice.models.entities.usuarios.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

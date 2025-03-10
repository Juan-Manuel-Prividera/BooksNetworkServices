package com.app.usersservice.services;

import com.app.usersservice.models.entities.usuarios.Rol;
import com.app.usersservice.models.entities.usuarios.RolType;
import com.app.usersservice.models.entities.usuarios.Usuario;
import com.app.usersservice.models.repositories.UsuarioRepository;
import com.app.usersservice.models.repositories.RolRepository;

public class UsuarioService {
  private final UsuarioRepository usuarioRepository;
  private final RolRepository rolRepository;

  public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
    this.usuarioRepository = usuarioRepository;
    this.rolRepository = rolRepository;
  }

  public void save(Usuario usuario) {
    usuarioRepository.save(usuario);
    // TODO: Falta hashear la contraseña
  }

  public Rol getRol(RolType rolType) {
    return rolRepository.findByRolType(rolType);
  }

}

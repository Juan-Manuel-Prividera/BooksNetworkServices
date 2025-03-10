package com.app.usersservice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.usersservice.models.dtos.LectorDTO;
import com.app.usersservice.models.dtos.RegistroDTO;
import com.app.usersservice.models.entities.usuarios.RolType;
import com.app.usersservice.models.entities.usuarios.Usuario;
import com.app.usersservice.services.UsuarioService;
import com.app.usersservice.services.LectorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/registro")
public class RegistroController {
  private final UsuarioService usuarioService;
  private final LectorService lectorService;

  public RegistroController(UsuarioService usuarioService, LectorService lectorService) {
    this.usuarioService = usuarioService;
    this.lectorService = lectorService;
  }

  @PostMapping("/lector")
  public ResponseEntity<Void> registrarLector(@RequestBody RegistroDTO registroDTO) {
    try {
      Usuario usuario = Usuario.builder()
        .username(registroDTO.getUsername())
        .password(registroDTO.getPassword())
        .roles(List.of(usuarioService.getRol(RolType.USER)))
        .build();

      usuarioService.save(usuario);
      
      LectorDTO lectorDTO = LectorDTO.builder()
        .nombre(registroDTO.getNombre())
        .apellido(registroDTO.getApellido())
        .email(registroDTO.getEmail())
        .telefono(registroDTO.getTelefono())
        .pais(registroDTO.getPais())
        .fechaNacimiento(registroDTO.getFechaNacimiento())
        .descripcion(registroDTO.getDescripcion())
        .tipoPerfil(registroDTO.getTipoPerfil())
        .imagenPerfil(registroDTO.getImagenPerfil())
        .usuarioId(usuario.getId())
        .build();
      
      lectorService.sendToLectorService(lectorDTO);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }
}

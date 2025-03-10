package com.app.usersservice.models.entities.usuarios;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rol {
  private Long id;
  private RolType rolType;
  private List<Permiso> permisos;
}

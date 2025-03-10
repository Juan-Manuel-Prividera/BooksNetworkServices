package com.app.usersservice.models.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDTO {
  private String username;
  private String password;
  private String email;
  private String nombre;
  private String apellido;
  private String telefono;
  private String pais;
  private LocalDate fechaNacimiento;
  private String descripcion;
  private String tipoPerfil;
  private String imagenPerfil;
}

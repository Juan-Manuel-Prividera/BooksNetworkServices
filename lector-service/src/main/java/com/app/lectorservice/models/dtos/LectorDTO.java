package com.app.lectorservice.models.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LectorDTO {
  private Long usuarioId;
  private String nombre;
  private String apellido;
  private String email;
  private String pais;
  private String descripcion;
  private String imagenPerfil;
  private String tipoPerfil;
  private LocalDate fechaNacimiento;
}

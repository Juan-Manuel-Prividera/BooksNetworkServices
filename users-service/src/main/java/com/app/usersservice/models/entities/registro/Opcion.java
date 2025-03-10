package com.app.usersservice.models.entities.registro;

import java.time.LocalDateTime;

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
public class Opcion {
  private Long id;
  private String descripcion;
  private boolean activo;
  private LocalDateTime fechaCreacion;
  private LocalDateTime fechaActualizacion;
}
package com.app.usersservice.models.entities.registro;

import java.time.LocalDateTime;
import java.util.List;

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
public class Cuestionario {
  private Long id;
  private List<Pregunta> preguntas;
  private String descripcion;
  private LocalDateTime fechaCreacion;
  private LocalDateTime fechaActualizacion;
  private boolean activo;
}

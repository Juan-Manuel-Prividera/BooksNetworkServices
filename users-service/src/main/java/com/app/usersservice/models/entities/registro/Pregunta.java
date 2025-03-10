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
public class Pregunta {
  private Long id;
  private String pregunta;
  private List<Opcion> opciones;
 
  private boolean activo;
  private LocalDateTime fechaCreacion;
  private LocalDateTime fechaActualizacion;
}
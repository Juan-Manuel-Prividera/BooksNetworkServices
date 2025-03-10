package com.app.usersservice.models.entities.registro;

import java.time.LocalDateTime;
import java.util.List;

import com.app.usersservice.models.entities.usuarios.Usuario;

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
public class Respuesta {
  private Long id;
  private Pregunta pregunta;
  private String respuesta;
  private List<Opcion> opciones;
  private LocalDateTime fechaRespuesta;
  private boolean activo;
  private Usuario usuario;
}
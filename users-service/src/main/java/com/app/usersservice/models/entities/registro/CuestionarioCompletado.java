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
public class CuestionarioCompletado {
  private Long id;
  private Cuestionario cuestionario;
  private List<Respuesta> respuestas;
  private Usuario usuario;
  private LocalDateTime fechaCompletado;
}
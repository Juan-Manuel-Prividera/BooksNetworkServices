package com.app.socialservice.models.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "comentarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comentario {
  @Id
  private String id;
  private String contenido;
  private String lectorId;
  private LocalDateTime fechaComentario;
  private Boolean activo;
  
  @DBRef
  private Publicacion publicacion;

  @DBRef(lazy = true)
  private Comentario comentarioPadre;
}

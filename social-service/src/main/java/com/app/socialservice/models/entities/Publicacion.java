package com.app.socialservice.models.entities;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "publicaciones")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Publicacion {
  @Id
  private String id;
  private String lectorPublicadorId;
  private String nombrePublicador;
  private String imagenPublicador;
  private String contenidoText;
  private String mediaUrl;
  private LocalDateTime fechaPublicacion;
  private Boolean activo;

  @DBRef(lazy = true)
  private Publicacion publicacionReposteada;
}
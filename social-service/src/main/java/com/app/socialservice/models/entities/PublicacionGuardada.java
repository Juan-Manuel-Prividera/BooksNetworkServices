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

@Document(collection = "publicaciones_guardadas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublicacionGuardada {
  @Id
  private String id;
  private String lectorId;
  @DBRef
  private Publicacion publicacion;
  private LocalDateTime fechaGuardado;
  private Boolean activo;
}

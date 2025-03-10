package com.app.socialservice.models.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
@Document(collection = "likes")
public class Like {
  @Id
  private String id;
  @Field("lector_id")
  private String lectorId;
  @Field("fecha_like")
  private LocalDateTime fechaLike;
  @Field("activo")
  private Boolean activo;
  @Field("review_id")
  private Long reviewId;
  
  // Relación Many-to-One con publicación o comentario
  @DBRef
  private Publicacion publicacion;

  @DBRef
  private Comentario comentario;
}

package com.app.socialservice.models.dtos;


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
public class PublicacionDTO {
  private String contenidoText;
  private String mediaUrl;

  private String lectorPublicadorId;
  private String nombrePublicador;
  private String imagenPublicador; 
}
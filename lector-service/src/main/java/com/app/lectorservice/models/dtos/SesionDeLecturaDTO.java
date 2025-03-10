package com.app.lectorservice.models.dtos;

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
public class SesionDeLecturaDTO {
  private Long lecturaId;
  private int pagina;
  private LocalDateTime fecha;
}
package com.app.lectorservice.models.entities.lecturas;

import java.time.Duration;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "sesiones_de_lectura")
public class SesionDeLectura {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "lectura_id")
  private Lectura lectura;
  @Column(name = "pagina_final")
  private int paginaFinal;
  @Column(name = "pagina_inicial")
  private int paginaInicial;
  @Column(name = "fecha_inicio")
  private LocalDateTime fechaInicio;
  @Column(name = "fecha_fin")
  private LocalDateTime fechaFin;
  @Column(name = "duracion")
  private Double duracion;


  public void calcularDuracion() {
    this.duracion = (double) Duration.between(fechaInicio, fechaFin).toMinutes();
  }
}

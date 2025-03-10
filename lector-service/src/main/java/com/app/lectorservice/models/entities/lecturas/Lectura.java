package com.app.lectorservice.models.entities.lecturas;

import java.time.LocalDateTime;

import com.app.lectorservice.models.entities.lectores.Lector;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "lecturas")
public class Lectura {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "libro_id")
  private Long libroId;
  @ManyToOne
  @JoinColumn(name = "lector_id")
  private Lector lector;
  @Column(name = "puntuacion")
  private Double puntuacion;
  @Column(name = "comentario")
  private String comentario;
  @Column(name = "progreso")
  private Double progreso;
  @Enumerated(EnumType.STRING)
  @Column(name = "estado")
  private EstadoLectura estado;
  @Column(name = "fecha_inicio")
  private LocalDateTime fechaInicio;
  @Column(name = "fecha_fin")
  private LocalDateTime fechaFin;

} 

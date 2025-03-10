package com.app.lectorservice.models.entities.lectores;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "lectores")
public class Lector {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "usuario_id", unique = true)
  private Long usuarioId;
  @Column(name = "nombre")
  private String nombre;
  @Column(name = "apellido")
  private String apellido;
  @Column(name = "email")
  private String email;
  @Column(name = "pais")
  private String pais;
  @Column(name = "fecha_nacimiento")
  private LocalDate fechaNacimiento;
  @Column(name = "activo")
  private boolean activo;
  @Column(name = "fecha_creacion")
  private LocalDateTime fechaCreacion;
  @Column(name = "fecha_actualizacion")
  private LocalDateTime fechaActualizacion;
  @Column(name = "imagen_perfil")
  private String imagenPerfil;
  @Column(name = "descripcion")
  private String descripcion;
  @Column(name = "tipo_perfil")
  @Enumerated(EnumType.STRING)
  private TipoPerfil tipoPerfil;
}
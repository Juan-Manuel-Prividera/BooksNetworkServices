package com.app.librosservice.models.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
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
@Builder(toBuilder = true)
@Entity
@Table(name = "libros")
public class Libro {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name = "volume_id", unique = true)
  private String volumeId;
  
  @Column(name = "titulo")
  private String titulo;
  
  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinTable(name = "libro_autor",
    joinColumns = @JoinColumn(name = "libro_id"),
    inverseJoinColumns = @JoinColumn(name = "autor_id"))
  private List<Autor> autores;
  
  @Column(name = "fecha_publicacion")
  private LocalDate fechaPublicacion;
  
  @Lob
  @Column(name = "descripcion", columnDefinition = "TEXT")
  private String descripcion;
  
  @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "isbn_id", referencedColumnName = "id")
  private Isbn isbn;
  
  @Column(name = "cantidad_paginas")
  private Long cantidadPaginas;
  
  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinTable(name = "libro_categoria",
    joinColumns = @JoinColumn(name = "libro_id"),
    inverseJoinColumns = @JoinColumn(name = "categoria_id"))
  private List<Categoria> categorias;
  
  @Column(name = "calificacion_promedio")
  private Double calificacionPromedio;
  
  @Column(name = "cantidad_calificaciones")
  private Double cantidadCalificaciones;
  
  @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "imagen_portada_id", referencedColumnName = "id")
  private Imagen imagenPortada;
  
  @Column(name = "idioma")
  private String idioma;


  public String toString() {
    return "Libro{" +
        "id=" + id +
        ", volumeId='" + volumeId + '\'' +
        ", titulo='" + titulo + '\'' +
        ", autores=" + autores.stream().map(Autor::getNombreCompleto).collect(Collectors.joining(", ")) +
        ", fechaPublicacion=" + fechaPublicacion +
        ", isbn=" + isbn +
        ", cantidadPaginas=" + cantidadPaginas +
        ", categorias=" + categorias.stream().map(Categoria::getNombreCategoria).collect(Collectors.joining(", ")) +
        ", calificacionPromedio=" + calificacionPromedio +
        ", cantidadCalificaciones=" + cantidadCalificaciones +
        ", imagenPortada=" + imagenPortada +
        ", idioma='" + idioma + '\'' +
        '}';
  }
}

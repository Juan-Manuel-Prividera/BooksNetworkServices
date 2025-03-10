package com.app.librosservice.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.app.librosservice.models.dtos.IndustryIdentifier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor   
@NoArgsConstructor
@ToString
@Entity
@Table(name = "isbn")
public class Isbn {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name = "isbn")
  private String isbn;
  
  @Column(name = "tipo")
  private String tipo;

  public Isbn(IndustryIdentifier industryIdentifier) {
    this.isbn = industryIdentifier.getIdentifier();
    this.tipo = industryIdentifier.getType();
  }
}
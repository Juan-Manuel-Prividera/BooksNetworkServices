package com.app.librosservice.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.app.librosservice.models.dtos.ImageLinks;

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
@Table(name = "imagenes")
public class Imagen {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name = "small_thumbnail")
  private String smallThumbnail;
  
  @Column(name = "thumbnail")
  private String thumbnail;
  
  @Column(name = "small")
  private String small;
  
  @Column(name = "medium")
  private String medium;
  
  @Column(name = "large")
  private String large;
  
  @Column(name = "extra_large")
  private String extraLarge;

  public Imagen(ImageLinks imageLinks) {
    if (imageLinks != null) {
      this.smallThumbnail = imageLinks.getSmallThumbnail();
      this.thumbnail = imageLinks.getThumbnail();
      this.small = imageLinks.getSmall();
      this.medium = imageLinks.getMedium();
      this.large = imageLinks.getLarge();
      this.extraLarge = imageLinks.getExtraLarge();
    }
  }
}

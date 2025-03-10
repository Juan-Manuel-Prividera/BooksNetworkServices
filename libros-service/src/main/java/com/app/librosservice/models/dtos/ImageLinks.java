package com.app.librosservice.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageLinks {
  private String smallThumbnail;
  private String thumbnail;
  private String small;
  private String medium;
  private String large;
  private String extraLarge;
}

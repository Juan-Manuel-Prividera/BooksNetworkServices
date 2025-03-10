package com.app.librosservice.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UniqueVolume {
  private String id;
  private VolumeInfo volumeInfo;
}
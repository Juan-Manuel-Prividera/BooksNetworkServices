package com.app.librosservice.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseApi {
  private String kind;
  private Long totalItems;
  private Item[] items;

}

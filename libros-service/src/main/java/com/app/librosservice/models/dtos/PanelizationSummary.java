package com.app.librosservice.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PanelizationSummary {
  private boolean containsEpubBubbles;
  private boolean containsImageBubbles;
}

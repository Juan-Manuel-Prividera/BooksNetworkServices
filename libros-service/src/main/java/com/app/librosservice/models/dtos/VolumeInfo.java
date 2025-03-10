package com.app.librosservice.models.dtos;

import java.time.LocalDate;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.app.librosservice.config.CustomLocalDateDeserializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VolumeInfo {
    private String title;
    private String subtitle;
    private String[] authors;
    private String publisher;
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    private LocalDate publishedDate;
    private String description;
    private IndustryIdentifier[] industryIdentifiers;
    private ReadingModes readingModes;
    private long pageCount;
    private String printType;
    private String[] categories;
    private double averageRating;
    private double ratingsCount;
    private String maturityRating;
    private boolean allowAnonLogging;
    private String contentVersion;
    private PanelizationSummary panelizationSummary;
    private ImageLinks imageLinks;
    private String language;
    private String previewLink;
    private String infoLink;
    private String canonicalVolumeLink; 
}

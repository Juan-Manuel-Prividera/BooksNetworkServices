package com.app.librosservice.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessInfo {
    private String country;
    private String viewability;
    private boolean embeddable;
    private boolean publicDomain;
    private String textToSpeechPermission;
    private Epub epub;
    private Epub pdf;
    private String webReaderLink;
    private String accessViewStatus;
    private boolean quoteSharingAllowed;
}

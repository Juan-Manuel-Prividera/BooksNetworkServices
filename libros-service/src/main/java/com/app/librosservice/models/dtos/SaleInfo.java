package com.app.librosservice.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleInfo {
    private String country;
    private String saleability;
    private boolean isEbook;
    private SaleInfoListPrice listPrice;
    private SaleInfoListPrice retailPrice;
    private String buyLink;
    private Offer[] offers; 
}

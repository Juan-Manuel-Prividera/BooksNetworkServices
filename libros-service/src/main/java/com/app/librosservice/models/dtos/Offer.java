package com.app.librosservice.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offer {
    private long finskyOfferType;
    private OfferListPrice listPrice;
    private OfferListPrice retailPrice;

}

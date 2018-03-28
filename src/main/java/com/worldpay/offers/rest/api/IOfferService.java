package com.worldpay.offers.rest.api;

import com.worldpay.offers.rest.OfferDTO;

import java.util.List;

public interface IOfferService {

    List<OfferDTO> getOfferList();
    OfferDTO getOffer(String id);
    OfferDTO postOffer(OfferDTO offerDTO);
    void cancelOffer(String id);
}

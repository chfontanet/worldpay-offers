package com.worldpay.offers.rest.implementation;

import com.worldpay.offers.dao.Offer;
import com.worldpay.offers.exceptions.OfferNotFoundException;
import com.worldpay.offers.persistence.ExpiringMap;
import com.worldpay.offers.rest.OfferDTO;
import com.worldpay.offers.rest.OfferMapper;
import com.worldpay.offers.rest.api.IOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService implements IOfferService {

    private ExpiringMap offers;
    private OfferMapper offerMapper;

    @Autowired
    public OfferService(ExpiringMap offers, OfferMapper offerMapper) {
        this.offers = offers;
        this.offerMapper = offerMapper;
    }

    @Override
    public List<OfferDTO> getOfferList() {
        return offerMapper.toOfferDtoList(offers.getOffers());
    }

    @Override
    public OfferDTO getOffer(String id) {
        final Offer offer = Optional.ofNullable(offers.getOffers().get(id))
                .orElseThrow(() -> new OfferNotFoundException("Offer with id " + id + " " + "not found!"));
        return offerMapper.toDto(offer);
    }

    @Override
    public OfferDTO postOffer(OfferDTO offerDTO) {
        final Offer offer = offerMapper.fromDto(offerDTO);
        offers.addOffer(offer);
        return offerDTO;
    }

    @Override
    public void cancelOffer(String id) {
        offers.cancelOffer(id);
    }
}

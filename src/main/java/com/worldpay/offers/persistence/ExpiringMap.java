package com.worldpay.offers.persistence;

import com.google.common.base.Strings;
import com.worldpay.offers.dao.Offer;
import com.worldpay.offers.exceptions.OfferArgumentException;
import com.worldpay.offers.exceptions.OfferExistentException;
import com.worldpay.offers.exceptions.OfferNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ExpiringMap {

    private Map<String, Offer> offers = new ConcurrentHashMap<>();

    public Map<String, Offer> getOffers() {
        removeExpiredOffers();
        return offers;
    }

    public ExpiringMap setOffers(Map<String, Offer> offers) {
        this.offers = offers;
        return this;
    }

    public ExpiringMap addOffer(Offer offer) {
        if ((offer.getProduct() == null) || (offer.getProduct().getId() == null)) throw new OfferArgumentException("Product is not valid");
        removeExpiredOffers();

        if (offers.containsKey(offer.getProduct().getId())) throw new OfferExistentException("This product is already in offer");
        offers.put(offer.getProduct().getId(), offer);

        return this;
    }

    public ExpiringMap cancelOffer(String id) {
        if (Strings.isNullOrEmpty(id)) throw new OfferArgumentException("Identifier of product is not valid");
        removeExpiredOffers();

        if (!offers.containsKey(id)) throw new OfferNotFoundException("This product is not actually in offer");
        offers.remove(id);

        return this;
    }

    private void removeExpiredOffers() {
        offers.values().removeIf(offer -> offer.getExpiringDate().isBeforeNow());
    }
}

package com.worldpay.offers.rest;

import com.worldpay.offers.dao.Offer;
import com.worldpay.offers.dao.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OfferMapper {

    public OfferDTO toDto(Offer offer) {

        return new OfferDTO()
                .setId(offer.getProduct().getId())
                .setName(offer.getProduct().getName())
                .setDescription(offer.getProduct().getDescription())
                .setPrice(offer.getProduct().getPrice())
                .setCurrency(offer.getProduct().getCurrency())
                .setExpiringDate(offer.getExpiringDate());
    }

    public Offer fromDto(OfferDTO offerDTO) {

        final Product product = new Product()
                .setId(offerDTO.getId())
                .setName(offerDTO.getName())
                .setDescription(offerDTO.getDescription())
                .setPrice(offerDTO.getPrice())
                .setCurrency(offerDTO.getCurrency());

        return new Offer()
                .setProduct(product)
                .setExpiringDate(offerDTO.getExpiringDate());
    }

    public List<OfferDTO> toOfferDtoList(Map<String, Offer> offers) {
        return offers.values().stream().map(this::toDto).collect(Collectors.toList());
    }
}

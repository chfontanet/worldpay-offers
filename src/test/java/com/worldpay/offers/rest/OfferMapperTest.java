package com.worldpay.offers.rest;

import com.worldpay.offers.dao.Offer;
import com.worldpay.offers.dao.Product;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.*;

public class OfferMapperTest {

    private final static String ID = "1";
    private final OfferMapper offerMapper = new OfferMapper();

    @Test
    public void testToDto() {

        DateTime dateTime = DateTime.now();
        assertEquals(getOfferDTO(ID, dateTime), offerMapper.toDto(getOffer(ID, dateTime)));
    }

    @Test
    public void testFromDto() {

        DateTime dateTime = DateTime.now();
        assertEquals(getOffer(ID, dateTime), offerMapper.fromDto(getOfferDTO(ID, dateTime)));
    }

    @Test
    public void testToOfferDtoList() {

        DateTime dateTime = DateTime.now();
        assertEquals(getOfferDTOList(dateTime), offerMapper.toOfferDtoList(getOffersMap(dateTime)));
    }

    private Offer getOffer(String id, DateTime dateTime) {
        Product product = new Product()
                .setId(id)
                .setName("product " + id)
                .setDescription("description of product " + id)
                .setPrice(9.99)
                .setCurrency(Currency.getInstance("GBP"));

        return new Offer()
                .setProduct(product)
                .setExpiringDate(dateTime);
    }

    private OfferDTO getOfferDTO(String id, DateTime dateTime) {
        return new OfferDTO()
                .setId(id)
                .setName("product " + id)
                .setDescription("description of product " + id)
                .setPrice(9.99)
                .setCurrency(Currency.getInstance("GBP"))
                .setExpiringDate(dateTime);
    }

    private Map<String, Offer> getOffersMap(DateTime dateTime) {

        Map<String, Offer> offersMap = new ConcurrentHashMap<>();
        offersMap.put("1", getOffer("1", dateTime));
        offersMap.put("2", getOffer("2", dateTime));
        offersMap.put("3", getOffer("3", dateTime));

        return offersMap;
    }

    private List<OfferDTO> getOfferDTOList(DateTime dateTime) {

        List<OfferDTO> offerDTOList = new ArrayList<>();
        offerDTOList.add(getOfferDTO("1", dateTime));
        offerDTOList.add(getOfferDTO("2", dateTime));
        offerDTOList.add(getOfferDTO("3", dateTime));

        return offerDTOList;
    }
}
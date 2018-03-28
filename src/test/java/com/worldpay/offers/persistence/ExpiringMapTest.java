package com.worldpay.offers.persistence;

import com.worldpay.offers.dao.Offer;
import com.worldpay.offers.dao.Product;
import com.worldpay.offers.exceptions.OfferArgumentException;
import com.worldpay.offers.exceptions.OfferExistentException;
import com.worldpay.offers.exceptions.OfferNotFoundException;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.Currency;

import static org.junit.Assert.*;

public class ExpiringMapTest {

    private static final String ID = "1";
    private ExpiringMap expiringMap;

    @Before
    public void setUp() {
        expiringMap = new ExpiringMap();
    }

    @Test
    public void testAddOffer() {
        expiringMap.addOffer(getOffer());

        assertTrue(expiringMap.getOffers().containsKey(ID));
    }

    @Test(expected = OfferArgumentException.class)
    public void testAddNullProduct() {
        expiringMap.addOffer(new Offer());
    }

    @Test(expected = OfferArgumentException.class)
    public void testAddOfferProductIdIsNull() {
        expiringMap.addOffer(new Offer().setProduct(new Product()));
    }

    @Test(expected = OfferExistentException.class)
    public void testAddExistingOffer() {
        expiringMap.addOffer(getOffer());
        expiringMap.addOffer(getOffer());
    }

    @Test
    public void testAddExistingOfferButExpired() {
        Offer offer = getOffer();
        expiringMap.addOffer(getOffer().setExpiringDate(DateTime.now().minus(1000)));
        expiringMap.addOffer(offer);

        assertEquals(expiringMap.getOffers().get(ID), offer);
    }

    @Test
    public void testCancelOffer() {
        expiringMap.addOffer(getOffer());
        assertTrue(expiringMap.getOffers().containsKey(ID));

        expiringMap.cancelOffer(ID);
        assertFalse(expiringMap.getOffers().containsKey(ID));
    }

    @Test(expected = OfferArgumentException.class)
    public void testCancelOfferProductIdIsNull() {
        expiringMap.cancelOffer(null);
    }

    @Test(expected = OfferArgumentException.class)
    public void testCancelProductIdIsBlank() {
        expiringMap.cancelOffer("");
    }

    @Test(expected = OfferNotFoundException.class)
    public void testCancelNotExistingOffer() {
        expiringMap.cancelOffer(ID);
    }

    @Test(expected = OfferNotFoundException.class)
    public void testCancelExistingOfferButExpired() {
        expiringMap.addOffer(getOffer().setExpiringDate(DateTime.now().minus(1000)));

        expiringMap.cancelOffer(ID);
    }

    private Offer getOffer() {
        Product product = new Product()
                .setId(ID)
                .setName("product 1")
                .setDescription("description of product 1")
                .setPrice(9.99)
                .setCurrency(Currency.getInstance("GBP"));

        return new Offer()
                .setProduct(product)
                .setExpiringDate(DateTime.now().plus(3600));
    }
}
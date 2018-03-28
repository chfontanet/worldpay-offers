package com.worldpay.offers.rest;

import com.google.common.collect.ImmutableList;
import com.worldpay.offers.exceptions.OfferExistentException;
import com.worldpay.offers.exceptions.OfferNotFoundException;
import com.worldpay.offers.persistence.ExpiringMap;
import com.worldpay.offers.rest.implementation.OfferService;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Currency;
import java.util.List;

import static org.junit.Assert.*;

public class OfferControllerTest {

    private OfferController controller;

    @Before
    public void setUp() {
        OfferService offerService = new OfferService(new ExpiringMap(), new OfferMapper());
        controller = new OfferController(offerService);
    }

    @Test
    public void testPost() {
        OfferDTO dto = getOfferDTO("1", DateTime.now().plus(60000));
        final ResponseEntity<OfferDTO> post = controller.post(dto);
        assertEquals(post.getStatusCode(), HttpStatus.CREATED);
        assertEquals(post.getBody(), dto);
    }

    @Test(expected = OfferExistentException.class)
    public void testPostExistentOffer() {
        OfferDTO dto = getOfferDTO("1", DateTime.now().plus(60000));
        controller.post(dto);
        controller.post(dto);
    }

    @Test
    public void testGetAll() {
        OfferDTO dto1 = getOfferDTO("1", DateTime.now().plus(60000));
        OfferDTO dto2 = getOfferDTO("2", DateTime.now().plus(60000));
        OfferDTO dto3 = getOfferDTO("3", DateTime.now().plus(60000));
        List<OfferDTO> offerDTOList = ImmutableList.of(dto1, dto2, dto3);

        controller.post(dto1);
        controller.post(dto2);
        controller.post(dto3);

        final ResponseEntity<List<OfferDTO>> entities = controller.get();
        assertEquals(entities.getStatusCode(), HttpStatus.OK);
        assertEquals(entities.getBody(), offerDTOList);
    }

    @Test
    public void testGetOne() {
        OfferDTO dto = getOfferDTO("1", DateTime.now().plus(60000));
        controller.post(dto);
        final ResponseEntity<OfferDTO> responseEntity = controller.get("1");
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(responseEntity.getBody(), dto);
    }

    @Test(expected = OfferNotFoundException.class)
    public void testGetOneNotFound() {
        controller.get("1");
    }

    @Test
    public void testDelete() {
        OfferDTO dto = getOfferDTO("1", DateTime.now().plus(60000));
        controller.post(dto);
        final ResponseEntity<?> responseEntity = controller.delete("1");
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NO_CONTENT);
        assertEquals(controller.get().getBody(), ImmutableList.of());
    }

    @Test(expected = OfferNotFoundException.class)
    public void testDeleteNotFound() {
        controller.delete("1");
    }

    private OfferDTO getOfferDTO(String id, DateTime dateTime) {
        return new OfferDTO()
                .setId(id)
                .setName("Name " + id)
                .setDescription("Description for name " + id)
                .setPrice(Double.parseDouble(id))
                .setCurrency(Currency.getInstance("GBP"))
                .setExpiringDate(dateTime);
    }
}
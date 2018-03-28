package com.worldpay.offers.rest;

import com.worldpay.offers.rest.api.IOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/offers", produces = MediaType.APPLICATION_JSON_VALUE)
public class OfferController {

    private IOfferService offerService;

    @Autowired
    public OfferController(IOfferService offerService) {
        this.offerService = offerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<OfferDTO>> get() {
        return ResponseEntity.ok(offerService.getOfferList());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<OfferDTO> get(@PathVariable("id") final String id) {
        return ResponseEntity.ok(offerService.getOffer(id));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferDTO> post(@Validated @RequestBody final OfferDTO offerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(offerService.postOffer(offerDTO));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") final String id) {
        offerService.cancelOffer(id);
        return ResponseEntity.noContent().build();
    }
}

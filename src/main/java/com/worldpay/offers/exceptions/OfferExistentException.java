package com.worldpay.offers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class OfferExistentException extends RuntimeException {

    public OfferExistentException(String message) {
        super(message);
    }
}

package com.worldpay.offers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OfferArgumentException extends RuntimeException {

    public OfferArgumentException(String message) {
        super(message);
    }
}

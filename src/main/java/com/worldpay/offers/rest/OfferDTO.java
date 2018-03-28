package com.worldpay.offers.rest;

import com.google.common.base.Objects;
import org.joda.time.DateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Currency;

public class OfferDTO {

    @NotEmpty
    private String id;
    private String name;
    private String description;
    @NotNull
    private Double price;
    @NotNull
    private Currency currency;
    @NotNull
    private DateTime expiringDate;

    public String getId() {
        return id;
    }

    public OfferDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OfferDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public OfferDTO setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Currency getCurrency() {
        return currency;
    }

    public OfferDTO setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public DateTime getExpiringDate() {
        return expiringDate;
    }

    public OfferDTO setExpiringDate(DateTime expiringDate) {
        this.expiringDate = expiringDate;
        return this;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferDTO offerDTO = (OfferDTO) o;
        return Objects.equal(id, offerDTO.id) &&
                Objects.equal(name, offerDTO.name) &&
                Objects.equal(description, offerDTO.description) &&
                Objects.equal(price, offerDTO.price) &&
                Objects.equal(currency, offerDTO.currency) &&
                Objects.equal(expiringDate, offerDTO.expiringDate);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(id, name, description, price, currency, expiringDate);
    }

    @Override
    public String toString() {

        return "OfferDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", currency=" + currency +
                ", expiringDate=" + expiringDate +
                '}';
    }
}

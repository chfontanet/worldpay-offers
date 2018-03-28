package com.worldpay.offers.dao;

import com.google.common.base.Objects;
import org.joda.time.DateTime;

public class Offer {

    private Product product;
    private DateTime expiringDate;

    public Product getProduct() {
        return product;
    }

    public Offer setProduct(Product product) {
        this.product = product;
        return this;
    }

    public DateTime getExpiringDate() {
        return expiringDate;
    }

    public Offer setExpiringDate(DateTime expiringDate) {
        this.expiringDate = expiringDate;
        return this;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return Objects.equal(product, offer.product) &&
                Objects.equal(expiringDate, offer.expiringDate);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(product, expiringDate);
    }

    @Override
    public String toString() {

        return "Offer{" +
                "product=" + product +
                ", expiringDate=" + expiringDate +
                '}';
    }
}

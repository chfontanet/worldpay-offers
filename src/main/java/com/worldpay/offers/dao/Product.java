package com.worldpay.offers.dao;

import com.google.common.base.Objects;

import java.util.Currency;

public class Product {

    private String id;
    private String name;
    private String description;
    private Double price;
    private Currency currency;

    public String getId() {
        return id;
    }

    public Product setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Product setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Product setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equal(id, product.id) &&
                Objects.equal(name, product.name) &&
                Objects.equal(description, product.description) &&
                Objects.equal(price, product.price) &&
                Objects.equal(currency, product.currency);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(id, name, description, price, currency);
    }

    @Override
    public String toString() {

        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", currency=" + currency +
                '}';
    }
}

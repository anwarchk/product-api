package com.anwar.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Anwar
 */

@Entity
@Table(name = "PRODUCT_PRICE")
public class ProductPrice extends BaseEntity {

    private BigDecimal price;
    private Product product;
    private PriceType priceType;
    private String currency;

    @Column(nullable = false, precision = 10, scale = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public PriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    @Column(name = "CURRENCY_CODE", length = 3, nullable = false)
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPrice that = (ProductPrice) o;
        return Objects.equals(price, that.price) &&
                Objects.equals(product, that.product) &&
                Objects.equals(priceType, that.priceType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, product, priceType);
    }

    public static ProductPrice build(Long id, BigDecimal price, PriceType priceType, String currency) {
        ProductPrice productPrice = new ProductPrice();
        productPrice.setId(id);
        productPrice.setPrice(price);
        productPrice.setPriceType(priceType);
        productPrice.setCurrency(currency);
        return productPrice;
    }

    public enum PriceType {
        REGULAR, SALE, CLEARANCE
    }

    @Override
    public String toString() {
        return "Id -> " + getId() + ", Product -> " + product + ", Price -> " + price + ", Price type ->" + priceType + ", Currency -> " + currency;
    }
}

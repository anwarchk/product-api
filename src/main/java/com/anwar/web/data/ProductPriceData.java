package com.anwar.web.data;

import com.anwar.domain.ProductPrice;

import java.text.NumberFormat;

/**
 * @author Anwar
 */

public class ProductPriceData {
    private Long id;
    private String price;
    private String priceType;
    private String currency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public static ProductPriceData valueOf(ProductPrice productPrice) {
        ProductPriceData productPriceData = new ProductPriceData();
        productPriceData.setId(productPrice.getId());
        NumberFormat nf = NumberFormat.getInstance();
        productPriceData.setPrice(nf.format(productPrice.getPrice()));
        productPriceData.setPriceType(productPrice.getPriceType().name());
        productPriceData.setCurrency(productPrice.getCurrency());
        return productPriceData;
    }
}

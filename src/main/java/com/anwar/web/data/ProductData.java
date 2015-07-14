package com.anwar.web.data;

import com.anwar.domain.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Anwar
 */

public class ProductData {

    private Long id;
    private String sku;
    private String name;
    @JsonProperty("category")
    private CategoryData category;
    @JsonProperty("prices")
    private List<ProductPriceData> productPrices;
    private String lastUpdated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryData getCategory() {
        return category;
    }

    public void setCategory(CategoryData category) {
        this.category = category;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<ProductPriceData> getProductPrices() {
        return productPrices;
    }

    public void setProductPrices(List<ProductPriceData> productPrices) {
        this.productPrices = productPrices;
    }

    public static ProductData valueOf(Product product) {
        ProductData productData = new ProductData();
        productData.setId(product.getId());
        productData.setName(product.getName());
        productData.setSku(product.getSku());
        return productData;
    }
}

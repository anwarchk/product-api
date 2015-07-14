package com.anwar.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author Anwar
 */

@Entity
public class Product extends BaseEntity {

    private String sku;
    private String name;
    private Category category;
    private Date lastUpdated;
    private Set<ProductPrice> productPrices;

    @Column(nullable = false, unique = true)
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @NotNull
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch = FetchType.EAGER, orphanRemoval = true)
    public Set<ProductPrice> getProductPrices() {
        return productPrices;
    }

    public void setProductPrices(Set<ProductPrice> productPrices) {
        this.productPrices = productPrices;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "LAST_UPDATED")
    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(sku, product.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }

    @Override
    public String toString() {
        return "Id : " + getId() + ", sku : " + sku + ", name : " + name;
    }

    public static Product build(Long id, String sku, String productName) {
        Product product = new Product();
        product.setName(productName);
        product.setSku(sku);
        product.setId(id);
        return product;
    }

    public static Product build(Long id, String sku, Category category, String productName, ProductPrice... prices) {
        Product product = new Product();
        product.setName(productName);
        product.setSku(sku);
        product.setId(id);
        product.setCategory(category);
        product.lastUpdated = new Date();
        if (prices != null) {
            Set<ProductPrice> productPrices = new LinkedHashSet<>(prices.length);
            for (ProductPrice productPrice : prices) {
                productPrice.setProduct(product);
                productPrices.add(productPrice);
            }
            product.setProductPrices(productPrices);
        }
        return product;
    }
}

package com.anwar.web.data;

import java.util.Collection;
import java.util.List;

/**
 * @author Anwar
 */

public class GetProductResponse {

    private Collection<ProductData> products;
    private PageableResultData pageData;

    public Collection<ProductData> getProducts() {
        return products;
    }

    public void setProducts(List<ProductData> products) {
        this.products = products;
    }

    public PageableResultData getPageData() {
        return pageData;
    }

    public void setPageData(PageableResultData pageData) {
        this.pageData = pageData;
    }

    public static GetProductResponse build(Collection<ProductData> products, PageableResultData pageableResultData) {
        GetProductResponse productResponse = new GetProductResponse();
        productResponse.products = products;
        productResponse.pageData = pageableResultData;
        return productResponse;
    }
}

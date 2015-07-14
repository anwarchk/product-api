package com.anwar.web.data.converter;

import com.anwar.domain.ProductPrice;
import com.anwar.web.data.ProductPriceData;

import javax.inject.Named;
import java.text.NumberFormat;

/**
 * @author Anwar
 */

@Named
public class ProductPriceDataConverter extends AbstractDataConverter<ProductPrice, ProductPriceData> {

    private final NumberFormat nf = NumberFormat.getInstance();

    @Override
    public ProductPriceData convert(ProductPrice productPrice) {
        ProductPriceData productPriceData = null;
        if (productPrice != null) {
            productPriceData = new ProductPriceData();
            productPriceData.setId(productPrice.getId());
            productPriceData.setPrice(nf.format(productPrice.getPrice()));
            productPriceData.setPriceType(productPrice.getPriceType().name());
            productPriceData.setCurrency(productPrice.getCurrency());
        }
        return productPriceData;
    }
}

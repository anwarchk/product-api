package com.anwar.web.data.converter;

import com.anwar.domain.Product;
import com.anwar.domain.ProductPrice;
import com.anwar.web.data.ProductData;
import com.anwar.web.data.ProductPriceData;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Anwar
 */

@Named
public class ProductDataConverter extends AbstractDataConverter<Product, ProductData> {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

    @Inject
    private CategoryDataConverter categoryDataConverter;

    @Inject
    private ProductPriceDataConverter productPriceDataConverter;

    @Override
    public ProductData convert(Product product) {
        ProductData productData = null;
        if (product != null) {
            productData = new ProductData();
            productData.setId(product.getId());
            productData.setSku(product.getSku());
            productData.setName(product.getName());
            productData.setCategory(categoryDataConverter.convert(product.getCategory()));
            List<ProductPriceData> prices;
            final Set<ProductPrice> productPrices = product.getProductPrices();
            if (!CollectionUtils.isEmpty(productPrices)) {
                prices = new ArrayList<>(productPrices.size());
                prices.addAll(productPriceDataConverter.convertAll(productPrices));
                productData.setProductPrices(prices);
            }
            productData.setLastUpdated(sdf.format(product.getLastUpdated()));
        }
        return productData;
    }
}

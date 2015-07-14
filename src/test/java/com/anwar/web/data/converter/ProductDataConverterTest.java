package com.anwar.web.data.converter;

import com.anwar.domain.*;
import com.anwar.web.data.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyCollection;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * @author Anwar
 */

@RunWith(MockitoJUnitRunner.class)
public class ProductDataConverterTest {

    @Mock
    private CategoryDataConverter categoryDataConverter;

    @Mock
    private ProductPriceDataConverter productPriceDataConverter;

    @InjectMocks
    private final ProductDataConverter converter = new ProductDataConverter();

    @Test
    public void shouldConvertFromProductToProductData() throws Exception {
        final Category category = Category.build(2L, "Test");
        final ProductPrice productPrice = ProductPrice.build(1l, BigDecimal.valueOf(12.99), ProductPrice.PriceType.CLEARANCE, "USD");
        final Product product = Product.build(1L, "ABCER123", category, "Lego Star Wars",
                productPrice);
        when(categoryDataConverter.convert(eq(category))).thenReturn(CategoryData.valueOf(category));
        when(productPriceDataConverter.convertAll(anyCollection())).thenReturn(Collections.singleton(ProductPriceData.valueOf(productPrice)));
        final ProductData productData = converter.convert(product);
        assertNotNull(productData);
        assertEquals(Long.valueOf(1), productData.getId());
        assertEquals("ABCER123", productData.getSku());
        assertEquals("Lego Star Wars", productData.getName());
        assertEquals("Test", productData.getCategory().getName());
        final ProductPriceData priceData = productData.getProductPrices().iterator().next();
        assertEquals("12.99", priceData.getPrice());
        assertEquals("USD", priceData.getCurrency());
        assertEquals("CLEARANCE", priceData.getPriceType());

    }
}
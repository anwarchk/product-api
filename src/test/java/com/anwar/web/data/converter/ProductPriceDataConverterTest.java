package com.anwar.web.data.converter;

import com.anwar.domain.ProductPrice;
import com.anwar.web.data.ProductPriceData;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * @author Anwar
 */
public class ProductPriceDataConverterTest {

    private final ProductPriceDataConverter converter = new ProductPriceDataConverter();

    @Test
    public void shouldConvertFromProductPriceToProductPriceData() throws Exception {
        final ProductPriceData productPriceData = converter.convert(ProductPrice.build(1L, BigDecimal.valueOf(12.99), ProductPrice.PriceType.REGULAR, "USD"));
        assertEquals(Long.valueOf(1), productPriceData.getId());
        assertEquals("12.99", productPriceData.getPrice());
        assertEquals("REGULAR", productPriceData.getPriceType());
        assertEquals("USD", productPriceData.getCurrency());
    }
}
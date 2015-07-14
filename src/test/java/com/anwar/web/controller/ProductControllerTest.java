package com.anwar.web.controller;

import com.anwar.domain.Product;
import com.anwar.service.ProductService;
import com.anwar.web.data.*;
import com.anwar.web.data.converter.PageableDataConverter;
import com.anwar.web.data.converter.ProductDataConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.*;

import java.util.Collections;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Anwar
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private PageableDataConverter pageableDataConverter;

    @Mock
    private ProductDataConverter productDataConverter;

    @InjectMocks
    private ProductController controller = new ProductController();

    @Test
    public void getProduct() throws Exception {
        final long productId = 1L;
        final Product product = Product.build(productId, "XYZ", "Product1");
        when(productService.getProduct(eq(productId))).thenReturn(product);
        when(productDataConverter.convert(eq(product))).thenReturn(new ProductData());
        final ProductData actual = controller.getProduct(productId);
        assertNotNull(actual);
        verify(productService).getProduct(eq(productId));
        verify(productDataConverter).convert(eq(product));
    }

    @Test
    public void getAllProducts() throws Exception {
        final long productId = 1L;
        Pageable pageable = new PageRequest(0, 10);
        final Product product = setUpProductData(productId);
        Page<Product> page = new PageImpl(Collections.singletonList(product), pageable, 1);
        when(productService.getProducts(any(Pageable.class))).thenReturn(page);
        final GetProductResponse actual = controller.getAllProducts(pageable);
        assertNotNull(actual);
        assertNotNull(actual.getProducts());
        assertNotNull(actual.getPageData());
        verify(productService).getProducts(any(Pageable.class));
        verify(productDataConverter).convertAll(anyCollection());
        verify(pageableDataConverter).convert(eq(page));
    }

    @Test
    public void getProductsByCategoryId() throws Exception {
        final long productId = 1L;
        final long categoryId = 2L;
        Pageable pageable = new PageRequest(0, 10);
        final Product product = setUpProductData(productId);
        Page<Product> page = new PageImpl(Collections.singletonList(product), pageable, 1);
        when(productService.getProductsByCategory(eq(categoryId), any(Pageable.class))).thenReturn(page);
        final GetProductResponse actual = controller.getProductsByCategoryId(categoryId, pageable);
        assertNotNull(actual);
        assertNotNull(actual.getProducts());
        assertNotNull(actual.getPageData());
        verify(productService).getProductsByCategory(eq(categoryId), any(Pageable.class));
        verify(productDataConverter).convertAll(anyCollection());
        verify(pageableDataConverter).convert(eq(page));
    }

    private Product setUpProductData(Long productId) {
        final Product product = Product.build(productId, "XYZ", "Product1");
        ProductData productData = ProductData.valueOf(product);
        when(productDataConverter.convertAll(anyCollection())).thenReturn(Collections.singleton(productData));
        when(pageableDataConverter.convert(any(Page.class))).thenReturn(new PageableResultData());
        return product;
    }
}
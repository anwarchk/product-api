package com.anwar.service.impl;

import com.anwar.domain.Product;
import com.anwar.repository.ProductRepository;
import com.anwar.service.ProductService;
import com.anwar.service.exception.NoProductsFoundException;
import com.anwar.service.exception.ProductNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.*;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Anwar
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService productService = new ProductServiceImpl();

    @Test
    public void shouldReturnExpectedProduct() throws Exception {
        final Long id = 1L;
        final Product expectedProduct = Product.build(id, "IOL123", "Optimus Prime");
        when(repository.findOne(eq(id))).thenReturn(expectedProduct);
        final Product actual = productService.getProduct(id);
        verify(repository).findOne(eq(id));
        assertEquals(expectedProduct, actual);
    }

    @Test(expected = ProductNotFoundException.class)
    public void shouldThrowExceptionWhenProductNotFound() throws Exception {
        when(repository.findOne(anyLong())).thenReturn(null);
        productService.getProduct(1L);
    }

    @Test
    public void shouldRetrieveAllProducts() throws Exception {
        Pageable pageable = new PageRequest(1, 10);
        Page<Product> expected = new PageImpl(Collections.singletonList(Product.build(1L, "IOL123", "Optimus Prime")), pageable, 1);
        when(repository.findAll(Matchers.any(PageRequest.class))).thenReturn(expected);
        final Page<Product> actual = productService.getProducts(pageable);
        assertEquals(expected, actual);
        verify(repository).findAll(Matchers.any(PageRequest.class));
    }

    @Test(expected = NoProductsFoundException.class)
    public void shouldThrowExceptionWhenNoProductsExists() {
        final PageRequest pageRequest = new PageRequest(1, 10);
        final Page<Product> page = new PageImpl<>(Collections.<Product>emptyList(), pageRequest, 0);
        when(repository.findAll(Matchers.any(PageRequest.class))).thenReturn(page);
        productService.getProducts(pageRequest);
    }

    @Test
    public void shouldRetrieveProductsByCategory() {
        Pageable pageable = new PageRequest(1, 10);
        Long categoryId = 1L;
        Page<Product> page = new PageImpl(Collections.singletonList(Product.build(1L, "IOL123", "Optimus Prime")), pageable, 1);
        when(repository.findByCategoryId(eq(categoryId), Matchers.any(PageRequest.class))).thenReturn(page);
        final Page<Product> results = productService.getProductsByCategory(categoryId, pageable);
        assertFalse(results.getContent().isEmpty());
    }

    @Test(expected = NoProductsFoundException.class)
    public void shouldThrowExceptionWhenProductsExistsForGivenCategory() {
        Pageable pageable = new PageRequest(1, 10);
        Long categoryId = 1L;
        final Page<Product> page = new PageImpl<>(Collections.<Product>emptyList(), pageable, 0);
        when(repository.findByCategoryId(eq(categoryId), Matchers.any(PageRequest.class))).thenReturn(page);
        productService.getProductsByCategory(categoryId, pageable);
    }
}
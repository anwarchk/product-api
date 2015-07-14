package com.anwar.repository;

import com.anwar.ProductApiApplicationTests;
import com.anwar.domain.*;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Anwar
 */
public class ProductRepositoryIntegrationTest extends ProductApiApplicationTests {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoryRepository categoryRepository;

    @Test
    public void shouldFindById() throws Exception {
        Category newCategory = Category.build("Test Category1");
        categoryRepository.save(newCategory);
        final Product product = Product.build(null, "ABC123", newCategory, "Test Product");
        productRepository.save(product);
        final Product actual = productRepository.findOne(product.getId());
        assertEquals(product, actual);
    }

    @Test
    public void shouldFindAll() throws Exception {
        final Page<Product> page = productRepository.findAll(new PageRequest(0, 10));
        assertNotNull(page);
        final List<Product> content = page.getContent();
        assertFalse(content.isEmpty());
    }

    @Test
    public void shouldLoadProductAndPrices() throws Exception {
        Category newCategory = Category.build("Test Category2");
        categoryRepository.save(newCategory);
        ProductPrice regular = ProductPrice.build(null, BigDecimal.valueOf(10.23), ProductPrice.PriceType.REGULAR, "USD");
        ProductPrice sale = ProductPrice.build(null, BigDecimal.valueOf(8.99), ProductPrice.PriceType.SALE, "USD");
        final Product product = Product.build(null, "ABC123", newCategory, "Test Product", regular, sale);
        productRepository.save(product);
        final Product actual = productRepository.findOne(product.getId());
        assertEquals(product, actual);
        assertTrue(actual.getProductPrices().size() == 2);
    }

    @Test
    public void shouldFindByCategory() throws Exception {
        Category newCategory = Category.build("Test Category3");
        categoryRepository.save(newCategory);
        final Product product = Product.build(null, "ABC123", newCategory, "Test Product");
        productRepository.save(product);
        final Page<Product> page = productRepository.findByCategoryId(newCategory.getId(), new PageRequest(0, 10));
        assertNotNull(page);
        final List<Product> content = page.getContent();
        assertFalse(content.isEmpty());
        assertTrue(content.size() == 1);
        final Product actual = content.listIterator().next();
        assertEquals(product, actual);
    }
}
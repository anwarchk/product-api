package com.anwar.service;

import com.anwar.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Anwar
 */

public interface ProductService {

    Product getProduct(final Long id);

    Page<Product> getProducts(final Pageable pageable);

    Page<Product> getProductsByCategory(final Long categoryId, Pageable pageable);
}

package com.anwar.service.impl;

import com.anwar.domain.Product;
import com.anwar.repository.ProductRepository;
import com.anwar.service.ProductService;
import com.anwar.service.exception.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Anwar
 */

@Named
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductRepository productRepository;

    @Override
    public Product getProduct(final Long id) {
        Product product = productRepository.findOne(id);
        if (product == null) {
            throw new ProductNotFoundException(ApiError.Errors._PRODUCT_NOT_FOUND, "Product with id : " + id + " not found.");
        }
        return product;
    }

    @Override
    public Page<Product> getProducts(final Pageable pageable) {
        final Page<Product> results = productRepository.findAll(pageable);
        if (CollectionUtils.isEmpty(results.getContent())) {
            throw new NoProductsFoundException(ApiError.Errors._PRODUCTS_NOT_FOUND, "There are no products available in the system");
        }
        return results;
    }

    @Override
    public Page<Product> getProductsByCategory(final Long categoryId, final Pageable pageable) {
        final Page<Product> results = productRepository.findByCategoryId(categoryId, pageable);
        if (CollectionUtils.isEmpty(results.getContent())) {
            throw new NoProductsFoundException(ApiError.Errors._PRODUCTS_NOT_FOUND, "There are no products available in the system for category : " + categoryId);
        }
        return results;
    }
}

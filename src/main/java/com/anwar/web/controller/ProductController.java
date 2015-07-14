package com.anwar.web.controller;

import com.anwar.domain.Product;
import com.anwar.service.ProductService;
import com.anwar.web.data.GetProductResponse;
import com.anwar.web.data.ProductData;
import com.anwar.web.data.converter.PageableDataConverter;
import com.anwar.web.data.converter.ProductDataConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * @author Anwar
 */

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Inject
    private ProductService productService;

    @Inject
    private PageableDataConverter pageableDataConverter;

    @Inject
    private ProductDataConverter productDataConverter;

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public ProductData getProduct(@PathVariable("productId") Long productId) {
        LOG.debug("Product requested with id : {}", productId);
        final Product product = productService.getProduct(productId);
        LOG.debug("Product retrieved : {}", product);
        return productDataConverter.convert(product);
    }

    @RequestMapping(method = RequestMethod.GET)
    public GetProductResponse getAllProducts(Pageable pageable) {
        final Page<Product> page = productService.getProducts(pageable);
        LOG.debug("Products retrieved :  {}", page);
        return GetProductResponse.build(productDataConverter.convertAll(page.getContent()), pageableDataConverter.convert(page));
    }

    @RequestMapping(method = RequestMethod.GET, params = {"categoryId"})
    public GetProductResponse getProductsByCategoryId(@RequestParam("categoryId") Long categoryId, Pageable pageable) {
        LOG.debug("Product requested for category id : {}", categoryId);
        final Page<Product> page = productService.getProductsByCategory(categoryId, pageable);
        LOG.debug("Products retrieved :  {}", pageable);
        return GetProductResponse.build(productDataConverter.convertAll(page.getContent()), pageableDataConverter.convert(page));
    }
}

package com.anwar.web.controller;

import com.anwar.ProductApiApplication;
import com.anwar.service.exception.ApiError;
import com.anwar.web.data.GetProductResponse;
import com.anwar.web.data.ProductData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author Anwar
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProductApiApplication.class)
@WebIntegrationTest({"server.port=9009"})
public class ProductControllerIntegrationTest {

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void shouldReturnProductData() {
        final ResponseEntity<ProductData> responseResponseEntity = restTemplate.getForEntity("http://localhost:9009/v1/products/{productId}", ProductData.class, 1L);
        assertEquals(HttpStatus.OK, responseResponseEntity.getStatusCode());
        assertTrue(responseResponseEntity.getHeaders().get("Content-Type").contains("application/json;charset=UTF-8"));
        final ProductData productData = responseResponseEntity.getBody();
        assertNotNull(productData);
        assertEquals("AEX143", productData.getSku());
    }

    @Test
    public void shouldReturnErrorResponse() {
        final ResponseEntity<ApiError> responseResponseEntity = restTemplate.getForEntity("http://localhost:9009/v1/products/{productId}", ApiError.class, 100L);
        assertEquals(HttpStatus.BAD_REQUEST, responseResponseEntity.getStatusCode());
        assertTrue(responseResponseEntity.getHeaders().get("Content-Type").contains("application/json;charset=UTF-8"));
        final ApiError apiError = responseResponseEntity.getBody();
        assertNotNull(apiError);
        assertEquals("_PRODUCT_NOT_FOUND", apiError.getErrorCode());
    }

    @Test
    public void shouldReturnProductsByCategory() {
        final ResponseEntity<GetProductResponse> responseResponseEntity = restTemplate.getForEntity("http://localhost:9009/v1/products?categoryId=1", GetProductResponse.class);
        assertEquals(HttpStatus.OK, responseResponseEntity.getStatusCode());
        assertTrue(responseResponseEntity.getHeaders().get("Content-Type").contains("application/json;charset=UTF-8"));
        final GetProductResponse productResponse = responseResponseEntity.getBody();
        assertNotNull(productResponse.getProducts());
        final ProductData productData = productResponse.getProducts().iterator().next();
        assertEquals("AEX143", productData.getSku());
        assertEquals(Long.valueOf(1), productData.getCategory().getId());
    }

    @Test
    public void shouldReturnProductsWithPaginationAndSorting() {
        final ResponseEntity<GetProductResponse> responseResponseEntity = restTemplate.getForEntity("http://localhost:9009/v1/products?page=0&size=10&sort=name,ASC", GetProductResponse.class);
        assertEquals(HttpStatus.OK, responseResponseEntity.getStatusCode());
        assertTrue(responseResponseEntity.getHeaders().get("Content-Type").contains("application/json;charset=UTF-8"));
        final GetProductResponse productResponse = responseResponseEntity.getBody();
        assertNotNull(productResponse);
        assertNotNull(productResponse.getProducts());
        assertNotNull(productResponse.getPageData());
        assertTrue(productResponse.getProducts().size() > 1);
        assertEquals(0, productResponse.getPageData().getPageNumber());
        assertEquals(10, productResponse.getPageData().getPageSize());
        final ProductData productData = productResponse.getProducts().iterator().next();
        //Should have sorted based on name
        assertEquals("Optimus Prime", productData.getName());
    }

    @Test
    public void shouldReturnErrorResponseForOutOfBoundPageRequest() {
        final ResponseEntity<ApiError> responseResponseEntity = restTemplate.getForEntity("http://localhost:9009/v1/products?page=1000&size=10&sort=name,ASC", ApiError.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseResponseEntity.getStatusCode());
        assertTrue(responseResponseEntity.getHeaders().get("Content-Type").contains("application/json;charset=UTF-8"));
        final ApiError apiError = responseResponseEntity.getBody();
        assertNotNull(apiError);
        assertEquals("_PRODUCTS_NOT_FOUND", apiError.getErrorCode());
    }

}

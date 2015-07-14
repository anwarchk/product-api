package com.anwar.repository;

import com.anwar.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Anwar
 */

/**
 * All CRUD methods in the underlying repository implementations are transactional by default.
 * For a fine grained transactional behavior, override specific methods and define
 *
 * @Transactional attributes.
 */
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);
}

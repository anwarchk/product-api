package com.anwar.repository;

import com.anwar.domain.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Anwar
 */
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
}

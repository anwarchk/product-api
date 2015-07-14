package com.anwar.web.data.converter;

import com.anwar.domain.Category;
import com.anwar.web.data.CategoryData;

import javax.inject.Named;

/**
 * @author Anwar
 */

@Named
public class CategoryDataConverter extends AbstractDataConverter<Category, CategoryData> {

    @Override
    public CategoryData convert(Category category) {
        CategoryData categoryData = null;
        if (category != null) {
            categoryData = new CategoryData();
            categoryData.setId(category.getId());
            categoryData.setName(category.getName());
        }
        return categoryData;
    }
}

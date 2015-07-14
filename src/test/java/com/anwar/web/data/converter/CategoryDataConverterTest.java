package com.anwar.web.data.converter;

import com.anwar.domain.Category;
import com.anwar.web.data.CategoryData;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Anwar
 */
public class CategoryDataConverterTest {

    private CategoryDataConverter converter = new CategoryDataConverter();

    @Test
    public void shouldConvertFromCategoryToCategoryData() throws Exception {
        Category category = Category.build(1L, "Test");
        final CategoryData categoryData = converter.convert(category);
        assertEquals(Long.valueOf(1), categoryData.getId());
        assertEquals("Test", categoryData.getName());
    }
}
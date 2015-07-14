package com.anwar.web.data;

import com.anwar.domain.Category;

/**
 * @author Anwar
 */

public class CategoryData {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static CategoryData valueOf(Category category) {
        CategoryData categoryData = new CategoryData();
        categoryData.setId(category.getId());
        categoryData.setName(category.getName());
        return categoryData;
    }
}

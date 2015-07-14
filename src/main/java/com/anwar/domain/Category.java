package com.anwar.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Anwar
 */

@Entity
@Cacheable
public class Category extends BaseEntity {

    private String name;

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Id : " + getId() + ", Name : " + name;
    }

    public static Category build(String categoryName) {
        Category category = new Category();
        category.setName(categoryName);
        return category;
    }

    public static Category build(Long id, String categoryName) {
        Category category = new Category();
        category.setId(id);
        category.setName(categoryName);
        return category;
    }
}

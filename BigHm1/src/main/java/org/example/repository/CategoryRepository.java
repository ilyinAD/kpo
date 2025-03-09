package org.example.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.example.domain.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryRepository {
    List<Category> categories = new ArrayList<Category>();

    public void save(Category category) {
        categories.add(category);
    }

    public List<Category> findAll() {
        return categories;
    }

    public void delete(int id) {
        categories.removeIf(category -> category.getId() == id);
    }
}

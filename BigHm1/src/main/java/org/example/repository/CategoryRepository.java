package org.example.repository;

import java.util.*;

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

    public Category findById(String id) {
        Optional<Category> category = categories.stream()
                .filter(user -> user.getId() == id)
                .findFirst();

        return category.orElse(null);
    }

    public void delete(String id) {
        categories.removeIf(category -> Objects.equals(category.getId(), id));
    }
}

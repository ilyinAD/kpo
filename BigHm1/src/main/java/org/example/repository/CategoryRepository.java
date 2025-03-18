package org.example.repository;

import java.util.*;

import org.example.domain.BankAccount;
import org.example.domain.Category;
import org.example.exceptions.InvalidArgumentException;
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

    public Category findById(String id) throws InvalidArgumentException {
        for (Category category : categories) {
            if (category.getId().equals(id)) {
                return category;
            }
        }

        throw new InvalidArgumentException("Category with id " + id + " does not exist");

    }

    public void delete(String id) {
        categories.removeIf(category -> Objects.equals(category.getId(), id));
    }
}

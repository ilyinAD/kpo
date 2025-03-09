package org.example.services;

import org.example.domain.Category;
import org.example.interfaces.SaveServiceInterface;
import org.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService implements SaveServiceInterface<Category> {
    CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void AddList(List<Category> categories) {
        categories.forEach(categoryRepository::save);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}

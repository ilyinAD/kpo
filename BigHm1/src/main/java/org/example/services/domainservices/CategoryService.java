package org.example.services.domainservices;

import org.example.domain.Category;
import org.example.exceptions.InvalidArgumentException;
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
    public void addList(List<Category> categories) throws InvalidArgumentException {
        for (Category category : categories) {
            add(category);
        }
    }

    public void add(Category category) throws InvalidArgumentException {
        boolean isNotUnique = categoryRepository.findAll().stream()
                .anyMatch(repAccount -> repAccount.getName().equals(category.getName()));
        if (isNotUnique) {
            throw new InvalidArgumentException("Account with this name already exists");
        }

        categoryRepository.save(category);
    }

    public Category findById(String id) throws InvalidArgumentException {
        return categoryRepository.findById(id);
    }

    public Category getByName(String name) throws InvalidArgumentException {
        Category category = categoryRepository.findAll()
               .stream()
               .filter(repAccount -> repAccount.getName().equals(name))
               .findFirst()
               .orElseThrow(() -> new InvalidArgumentException("Account with this name does not exist"));
        return category;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}

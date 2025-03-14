package org.example.controllers;

import org.example.domain.Category;
import org.example.services.domainservices.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CategoryController {
    CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    public void addCategory(Category category) {
        categoryService.add(category);
    }
    public void printAllCategories() {
        categoryService.getAllCategories().forEach(System.out::println);
    }

}

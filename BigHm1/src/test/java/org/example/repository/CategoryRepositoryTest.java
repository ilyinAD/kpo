package org.example.repository;

import org.example.builders.CategoryBuilder;
import org.example.domain.Category;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryRepositoryTest {
    CategoryRepository categoryRepository = new CategoryRepository();

    @Test
    void saveCategoryTest() {
        try {
            Category category = new CategoryBuilder()
                    .setName("работа")
                    .setType("доход")
                    .build();
            categoryRepository.save(category);
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }

    @Test
    void saveWithWrongOperationType() {
        Exception exception = assertThrows(Exception.class, () -> {
            Category category = new CategoryBuilder()
                    .setName("работа")
                    .setType("доход")
                    .build();
            categoryRepository.save(category);

            Category category1 = new CategoryBuilder()
                    .setName("работа")
                    .setType("расход1")
                    .build();
            categoryRepository.save(category1);
        });

        assertEquals(exception.getMessage(), "Invalid operation type: расход1");
    }

    @Test
    void findAllCategoriesTest() {
        try {
            Category category = new CategoryBuilder()
                    .setName("работа")
                    .setType("доход")
                    .build();
            categoryRepository.save(category);

            List<Category> categories = categoryRepository.findAll();
            assertEquals(1, categories.size());
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }

    @Test
    void findCategoryByIdTest() {
        try {
            Category category = new CategoryBuilder()
                    .setName("работа")
                    .setType("доход")
                    .build();
            categoryRepository.save(category);

            Category foundCategory = categoryRepository.findById(category.getId());
            assertEquals(category, foundCategory);
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }

    @Test
    void deleteCategoryTest() {
        try {
            Category category = new CategoryBuilder()
                    .setName("работа")
                    .setType("доход")
                    .build();
            categoryRepository.save(category);

            categoryRepository.delete(category.getId());

            Category foundCategory = categoryRepository.findById(category.getId());
            assertNull(foundCategory);
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }

    @Test
    void findByNotExistedIdTest() {
        try {
            Category category = new CategoryBuilder()
                    .setName("работа")
                    .setType("доход")
                    .build();
            categoryRepository.save(category);

            Category foundCategory = categoryRepository.findById("1");
            assertNull(foundCategory);
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }
}

package org.example.services.domainservices;

import org.example.domain.BankAccount;
import org.example.domain.Category;
import org.example.domain.CategoryFactory;
import org.example.exceptions.InvalidArgumentException;
import org.example.repository.BankAccountRepository;
import org.example.repository.CategoryRepository;
import org.example.services.domainservices.BankAccountService;
import org.example.services.domainservices.CategoryService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CategoryServiceTest {
    private CategoryService service = new CategoryService(new CategoryRepository());

    private List<Category> categories;

    @Test
    public void AddListOfCategoriesWithoutDuplicates() {
        try {
            categories = Arrays.asList(
                    CategoryFactory.create("доход", "category1"),
                    CategoryFactory.create("расход", "category2"),
                    CategoryFactory.create("доход", "category3")
            );
        } catch (Exception e) {
            fail(e.getMessage());
        }

        try {
            service.addList(categories);
            assertEquals(service.getAllCategories(), categories);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void AddListOfCategoriesWithDuplicate() {
        try {
            categories = Arrays.asList(
                    CategoryFactory.create("доход", "category1"),
                    CategoryFactory.create("расход", "category2"),
                    CategoryFactory.create("доход", "category1")
            );
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertThrows(InvalidArgumentException.class, () -> service.addList(categories));
    }

    @Test
    public void FindExistCategoryByName() {
        try {
        categories = Arrays.asList(
                CategoryFactory.create("доход", "category1"),
                CategoryFactory.create("расход", "category2"),
                CategoryFactory.create("доход", "category3")
        );
        } catch (Exception e) {
            fail(e.getMessage());
        }
        try {
            service.addList(categories);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        try {
            Category category = service.getByName("category2");
            assertEquals(category, categories.get(1));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void FindNonExistCategoryByName() {
        try {
        categories = Arrays.asList(
                CategoryFactory.create("доход", "category1"),
                CategoryFactory.create("расход", "category2"),
                CategoryFactory.create("доход", "category3")
        );
        } catch (Exception e) {
            fail(e.getMessage());
        }
        try {
            service.addList(categories);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertThrows(InvalidArgumentException.class, () -> service.getByName("category"));
    }

    @Test
    public void FindExistCategoryById() {
        try {
        categories = Arrays.asList(
                CategoryFactory.create("доход", "category1"),
                CategoryFactory.create("расход", "category2"),
                CategoryFactory.create("доход", "category3")
        );
        } catch (Exception e) {
            fail(e.getMessage());
        }
        try {
            service.addList(categories);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        try {
            Category category = service.findById(service.getByName("category2").getId());
            assertEquals(category, categories.get(1));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void FindNonExistCategoryById() {
        try {
        categories = Arrays.asList(
                CategoryFactory.create("доход", "category1"),
                CategoryFactory.create("расход", "category2"),
                CategoryFactory.create("доход", "category3")
        );
        } catch (Exception e) {
            fail(e.getMessage());
        }
        try {
            service.addList(categories);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertThrows(InvalidArgumentException.class, () -> service.findById("incorrect id"));
    }

    @Test
    public void GetAllCategories() {
        try {
        categories = Arrays.asList(
                CategoryFactory.create("доход", "category1"),
                CategoryFactory.create("расход", "category2"),
                CategoryFactory.create("доход", "category3")
        );
        } catch (Exception e) {
            fail(e.getMessage());
        }
        try {
            service.addList(categories);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertEquals(categories, service.getAllCategories());
    }


}

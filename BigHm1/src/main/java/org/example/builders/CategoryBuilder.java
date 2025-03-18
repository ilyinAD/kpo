package org.example.builders;

import org.example.domain.Category;
import org.example.domain.CategoryFactory;
import org.example.exceptions.InvalidArgumentException;

public class CategoryBuilder {
    private String type;
    private String name;

    public CategoryBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public CategoryBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Category build() throws InvalidArgumentException {
        return CategoryFactory.create(type, name);
    }
}

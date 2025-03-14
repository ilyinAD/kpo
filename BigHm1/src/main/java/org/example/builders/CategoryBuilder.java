package org.example.builders;

import org.example.domain.Category;

public class CategoryBuilder {
    private int id;
    private String type;
    private String name;

    public CategoryBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public CategoryBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public CategoryBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Category build() {
        return Category.create(id, name, type);
    }
}

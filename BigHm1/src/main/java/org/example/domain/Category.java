package org.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Category {
    private final int id;
    private final String type;
    private final String name;

    private Category(int id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    @JsonCreator
    public static Category create(@JsonProperty("id") int id, @JsonProperty("type") String type, @JsonProperty("name") String name) {
        return new Category(id, type, name);
    }

    @Override
    public String toString() {
        return STR."Category{id=\{id}, type='\{type}\{'\''}, name='\{name}\{'\''}\{'}'}";
    }
}
package org.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Category {
    private final int id;
    private final OperationType type;
    private final String name;

    private Category(int id, OperationType type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    @JsonCreator
    public static Category create(@JsonProperty("id") int id, @JsonProperty("type") String type, @JsonProperty("name") String name) {
        OperationType operationType = OperationType.fromString(type);
        if (operationType == null) {
            throw new IllegalArgumentException("Invalid operation type: " + type);
        }

        return new Category(id, operationType, name);
    }

    @Override
    public String toString() {
        return STR."Category{id=\{id}, type='\{type}\{'\''}, name='\{name}\{'\''}\{'}'}";
    }
}
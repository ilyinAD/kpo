package org.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.example.exceptions.InvalidArgumentException;

import java.util.UUID;

@Getter
public class Category {
    private final String id;
    private final OperationType type;
    private final String name;

    private Category(String id, OperationType type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    @JsonCreator
    public static Category create(@JsonProperty("type") String type, @JsonProperty("name") String name) throws InvalidArgumentException {
        OperationType operationType = OperationType.fromString(type);
        if (operationType == null) {
            throw new InvalidArgumentException(STR."Invalid operation type: \{type}");
        }

        String id = UUID.randomUUID().toString();

        return new Category(id, operationType, name);
    }

    public static Category create(String id, String type, String name) throws InvalidArgumentException {
        OperationType operationType = OperationType.fromString(type);
        if (operationType == null) {
            throw new InvalidArgumentException(STR."Invalid operation type: \{type}");
        }

        return new Category(id, operationType, name);
    }

    @Override
    public String toString() {
        return STR."Category{id=\{id}, type='\{type}\{'\''}, name='\{name}\{'\''}\{'}'}";
    }
}
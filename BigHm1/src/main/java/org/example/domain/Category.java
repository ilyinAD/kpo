package org.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.example.exceptions.InvalidArgumentException;

import java.util.UUID;
import java.util.logging.Logger;

@Getter
public class Category {
    private static final Logger logger = Logger.getLogger(Category.class.getName());
    private final String id;
    private final OperationType type;
    private final String name;

    Category(String id, OperationType type, String name) {
        logger.info("private constructor category");
        this.id = id;
        this.type = type;
        this.name = name;
    }

//    @JsonCreator
//    private static Category create(@JsonProperty("type") String type, @JsonProperty("name") String name) throws InvalidArgumentException {
//        OperationType operationType = OperationType.fromString(type);
//        if (operationType == null) {
//            throw new InvalidArgumentException("Invalid operation type: " + type);
//        }
//
//        String id = UUID.randomUUID().toString();
//
//        return new Category(id, operationType, name);
//    }
    @JsonCreator
    public static Category create(@JsonProperty("id") String id, @JsonProperty("type") String type, @JsonProperty("name") String name) throws InvalidArgumentException {
        logger.info("jackson create category");
        return CategoryFactory.create(id, type, name);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';

        //return "Category{id=\{id}, type='\{type}\{'\''}, name='\{name}\{'\''}\{'}'}";
    }
}
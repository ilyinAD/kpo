package org.example.domain;

import org.example.exceptions.InvalidArgumentException;

import java.util.UUID;
import java.util.logging.Logger;

public class CategoryFactory {
    private static final Logger logger = Logger.getLogger(CategoryFactory.class.getName());
    public static Category create(String type, String name) throws InvalidArgumentException {
        logger.info("fabric create category");
        String id = UUID.randomUUID().toString();

        return create(id, type, name);
    }

    static Category create(String id, String type, String name) throws InvalidArgumentException {
        logger.info("fabric final create category");
        OperationType operationType = OperationType.fromString(type);
        if (operationType == null) {
            throw new InvalidArgumentException("Invalid operation type: " + type);
        }

        return new Category(id, operationType, name);
    }
}

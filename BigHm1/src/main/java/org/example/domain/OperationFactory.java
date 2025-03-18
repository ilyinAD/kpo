package org.example.domain;

import org.example.exceptions.InvalidArgumentException;

import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

public class OperationFactory {
    private static final Logger logger = Logger.getLogger(OperationFactory.class.getName());
    public static Operation create(String type, String bankAccountId,
                                            double amount, Date date, String description,
                                            String categoryId) throws InvalidArgumentException {
        logger.info("fabric create operation");
        String id = UUID.randomUUID().toString();

        return create(id, type, bankAccountId, amount, date, description, categoryId);
    }

    static Operation create(String id, String type, String bankAccountId,
                            double amount, Date date, String description,
                            String categoryId) throws InvalidArgumentException {
        logger.info("fabric final create operation");

        if (amount < 0) throw new InvalidArgumentException("Amount can't be negative");
        OperationType operationType = OperationType.fromString(type);
        if (operationType == null) {
            throw new InvalidArgumentException("Invalid operation type: " + type);
        }

        return new Operation(id, operationType, bankAccountId, amount, date, description, categoryId);
    }
}

package org.example.repository;

import org.example.builders.OperationBuilder;
import org.example.domain.Operation;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OperationRepositoryTest {
    OperationRepository operationRepository = new OperationRepository();

    @Test
    void saveTest() {
        try {
            Operation operation = new OperationBuilder()
                    .setAmount(100)
                    .setDate("2022-01-01")
                    .setDescription("Test operation")
                    .setType("доход")
                    .setBankAccountId("1234567890")
                    .setCategoryId("123")
                    .build();
            operationRepository.save(operation);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void findAllTest() {
        try {
            Operation operation = new OperationBuilder()
                    .setAmount(100)
                    .setDate("2022-01-01")
                    .setDescription("Test operation")
                    .setType("доход")
                    .setBankAccountId("1234567890")
                    .setCategoryId("123")
                    .build();
            operationRepository.save(operation);

            Operation operation1 = new OperationBuilder()
                    .setAmount(110)
                    .setDate("2022-01-01")
                    .setDescription("Test operation")
                    .setType("доход")
                    .setBankAccountId("1234567890")
                    .setCategoryId("123")
                    .build();
            operationRepository.save(operation);

            assertEquals(operationRepository.findAll().size(), 2);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void deleteTest() {
        try {
            Operation operation = new OperationBuilder()
                    .setAmount(100)
                    .setDate("2022-01-01")
                    .setDescription("Test operation")
                    .setType("доход")
                    .setBankAccountId("1234567890")
                    .setCategoryId("123")
                    .build();
            operationRepository.save(operation);

            operationRepository.delete(operation.getId());

            assertEquals(operationRepository.findAll().size(), 0);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void saveWithWrongOperationTypeTest() {
        Exception exception = assertThrows(Exception.class, ()->{
            Operation operation = new OperationBuilder()
                    .setAmount(100)
                    .setDate("2022-01-01")
                    .setDescription("Test operation")
                    .setType("доход1")
                    .setBankAccountId("1234567890")
                    .setCategoryId("123")
                    .build();
        }
        );
        assertEquals(exception.getMessage(), "Invalid operation type: доход1");
    }

    @Test
    void saveWithWrongAmountTest() {
        Exception exception = assertThrows(Exception.class, ()->{
                    Operation operation = new OperationBuilder()
                            .setAmount(-100)
                            .setDate("2022-01-01")
                            .setDescription("Test operation")
                            .setType("доход")
                            .setBankAccountId("1234567890")
                            .setCategoryId("123")
                            .build();
                }
        );

        assertEquals(exception.getMessage(), "Amount can't be negative");
    }

    @Test
    void saveWithWrongDateTest() {
        assertThrows(ParseException.class, ()->{
                    Operation operation = new OperationBuilder()
                            .setAmount(100)
                            .setDate("2022120101")
                            .setDescription("Test operation")
                            .setType("доход")
                            .setBankAccountId("1234567890")
                            .setCategoryId("123")
                            .build();
                }
        );
    }
}

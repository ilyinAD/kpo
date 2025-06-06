package org.example.repository;

import org.example.builders.OperationBuilder;
import org.example.constants.Constants;
import org.example.domain.Operation;
import org.example.domain.OperationFactory;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.scanner.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OperationRepositoryTest {
    OperationRepository operationRepository = new OperationRepository();

    @Test
    void saveTest() {
        try {
            Operation operation = OperationFactory.create("доход", "1234567890", 100,
                    Constants.DateFormat.parse("2022-01-01"), "Test operation", "123");
            operationRepository.save(operation);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void findAllTest() {
        try {
            Operation operation = OperationFactory.create("доход", "1234567890", 100,
                    Constants.DateFormat.parse("2022-01-01"), "Test operation", "123");

            operationRepository.save(operation);

            Operation operation1 = OperationFactory.create("доход", "1234567890", 110,
                    Constants.DateFormat.parse("2022-01-01"), "Test operation", "123");
            operationRepository.save(operation1);

            assertEquals(operationRepository.findAll().size(), 2);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void deleteTest() {
        try {
            Operation operation = OperationFactory.create("доход", "1234567890", 100,
                    Constants.DateFormat.parse("2022-01-01"), "Test operation", "123");
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
                    Operation operation = OperationFactory.create("доход1", "1234567890", 100,
                            Constants.DateFormat.parse("2022-01-01"), "Test operation", "123");
        }
        );
        assertEquals(exception.getMessage(), "Invalid operation type: доход1");
    }

    @Test
    void saveWithWrongAmountTest() {
        Exception exception = assertThrows(Exception.class, ()->{
            Operation operation = OperationFactory.create("доход", "1234567890", -100,
                    Constants.DateFormat.parse("2022-01-01"), "Test operation", "123");
        }
        );

        assertEquals(exception.getMessage(), "Amount can't be negative");
    }

    @Test
    void saveWithWrongDateTest() {
        assertThrows(ParseException.class, ()->{
            Operation operation = OperationFactory.create("доход", "1234567890", 100,
                    Constants.DateFormat.parse("20220101"), "Test operation", "123");
                }
        );
    }
}

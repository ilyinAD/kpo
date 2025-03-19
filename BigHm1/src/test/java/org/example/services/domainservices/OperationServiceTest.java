package org.example.services.domainservices;

import org.example.builders.BankAccountBuilder;
import org.example.constants.Constants;
import org.example.domain.BankAccount;
import org.example.domain.Operation;
import org.example.domain.OperationFactory;
import org.example.repository.OperationRepository;
import org.example.services.domainservices.OperationService;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OperationServiceTest {
    OperationService operationService = new OperationService(new OperationRepository());

    @Test
    public void saveTest() {
        try {
            Operation operation = OperationFactory.create("доход", "1234567890", 100,
                    Constants.DateFormat.parse("2022-01-01"), "Test operation", "123");
            operationService.add(operation);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void findAllTest() {
        try {
            Operation operation = OperationFactory.create("доход", "1234567890", 100,
                    Constants.DateFormat.parse("2022-01-01"), "Test operation", "123");

            operationService.add(operation);

            Operation operation1 = OperationFactory.create("доход", "1234567890", 110,
                    Constants.DateFormat.parse("2022-01-01"), "Test operation", "123");
            operationService.add(operation1);

            assertEquals(operationService.getAllOperations().size(), 2);
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

package org.example.domain;

import org.example.constants.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;

@ExtendWith(MockitoExtension.class)
public class OperationTest {
    @Test
    public void toStringTest() {
        try {
            Operation operation = OperationFactory.create("доход", "1234567890", 100,
                    Constants.DateFormat.parse("2022-01-01"), "Test operation", "123");
            operation.toString();
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }
}

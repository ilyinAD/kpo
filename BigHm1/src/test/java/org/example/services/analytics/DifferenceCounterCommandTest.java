package org.example.services.analytics;

import org.example.constants.Constants;
import org.example.domain.Operation;
import org.example.domain.OperationFactory;
import org.example.services.domainservices.BankFacade;
import org.example.services.domainservices.OperationService;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@RunWith(MockitoJUnitRunner.class)
public class DifferenceCounterCommandTest {
//    @Mock
//    BankFacade bankFacade;

//    @InjectMocks
//    DifferenceCounterCommand differenceCounterCommand;

    @Test
    public void executeTest() {
        try {
            BankFacade bankFacade = mock(BankFacade.class);
            DifferenceCounterCommand differenceCounterCommand = new DifferenceCounterCommand(bankFacade);
            OperationService operationService = mock(OperationService.class);
            when(bankFacade.getOperationService()).thenReturn(operationService);
            List<Operation> operations = new ArrayList<>();
            operations.add(OperationFactory.create("доход", "1234567890", 100,
                    Constants.DateFormat.parse("2022-01-01"), "Test operation", "123"));
            when(operationService.getAllOperations()).thenReturn(operations);
            Double ans = differenceCounterCommand.execute(Date.valueOf("2020-10-10"), Date.valueOf("2024-10-10"));
            assertEquals(ans, 100);
        } catch (Exception e) {
            fail("Test failed " + e.getMessage());
        }

    }
}

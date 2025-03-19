package org.example.services.analytics;

import org.example.constants.Constants;
import org.example.domain.*;
import org.example.services.domainservices.BankAccountService;
import org.example.services.domainservices.BankFacade;
import org.example.services.domainservices.CategoryService;
import org.example.services.domainservices.OperationService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RecountBalanceCommandTest {
    @Test
    public void executeTest() {
        try {
        BankFacade bankFacade = mock(BankFacade.class);
        RecountBalanceCommand command = new RecountBalanceCommand(bankFacade);
        BankAccountService bankAccountService = mock(BankAccountService.class);
        List<BankAccount> accounts = new ArrayList<BankAccount>();
            accounts.add(BankAccountFactory.create("max", 100));
        when(bankFacade.getBankAccountService()).thenReturn(bankAccountService);
        when(bankAccountService.getAllAccounts()).thenReturn(accounts);

        OperationService operationService = mock(OperationService.class);
        List<Operation> operations = new ArrayList<>();
        operations.add(OperationFactory.create("доход", "1234567890", 100,
                Constants.DateFormat.parse("2022-01-01"), "Test operation", "123"));
        when(bankFacade.getOperationService()).thenReturn(operationService);
        when(operationService.getAllOperations()).thenReturn(operations);

        command.execute();

        } catch (Exception e) {
            fail("Test failed " + e.getMessage());
        }
    }
}

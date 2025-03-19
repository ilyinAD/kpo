package org.example.builders;
import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.mockito.Mockito.*;

import org.example.constants.Constants;
import org.example.controllers.DomainFacadeController;
import org.example.domain.*;
import org.example.exceptions.InvalidArgumentException;
import org.example.services.domainservices.BankAccountService;
import org.example.services.domainservices.BankFacade;
import org.example.services.domainservices.CategoryService;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.mockito.junit.MockitoJUnitRunner;
//@RunWith(MockitoJUnitRunner.class)
public class OperationBuilderTest {
//    @Mock
//    BankFacade bankFacade;
//
//    @InjectMocks
//    OperationBuilder operationBuilder;

    @Test
    public void createOperationCorrectTest() throws InvalidArgumentException {
        BankFacade bankFacade = mock(BankFacade.class);
        BankAccountService bankAccountService = mock(BankAccountService.class);
        BankAccount bankAccount = mock(BankAccount.class);

        when(bankFacade.getBankAccountService()).thenReturn(bankAccountService);

        doReturn(bankAccount).when(bankAccountService).getByName("Test account");

        when(bankAccount.getId()).thenReturn("1");

        CategoryService categoryService = mock(CategoryService.class);
        Category category = mock(Category.class);

        when(bankFacade.getCategoryService()).thenReturn(categoryService);
        doReturn(category).when(categoryService).getByName("Test category");
        when(category.getId()).thenReturn("2");
        when(category.getType()).thenReturn(OperationType.INCOME);

        OperationBuilder operationBuilder = new OperationBuilder(bankFacade);

        try {
            Operation operation = operationBuilder
                    .setAmount(100)
                    .setDate("2020-10-10")
                    .setDescription("Test description")
                    .setCategoryName("Test category")
                    .setBankAccountName("Test account")
                    .build();
            Operation correctOperation = OperationFactory.create("доход", "1", 100, Constants.DateFormat.parse("2020-10-10"), "Test description", "2");
            assertThat(operation).usingRecursiveComparison()
                    .ignoringFields("id")
                    .isEqualTo(correctOperation);
        } catch (Exception e) {
            fail("Expected no exception, but got: " + e.getMessage());
        }

    }

//    @Test
//    public void createOperationInvalidCategoryNameTest() throws InvalidArgumentException {
//        BankFacade bankFacade = mock(BankFacade.class);
//        BankAccountService bankAccountService = mock(BankAccountService.class);
//        BankAccount bankAccount = mock(BankAccount.class);
//
//        when(bankFacade.getBankAccountService()).thenReturn(bankAccountService);
//
//        doReturn(bankAccount).when(bankAccountService).getByName("Test account");
//
//        when(bankAccount.getId()).thenReturn("1");
//
//        CategoryService categoryService = mock(CategoryService.class);
//        Category category = mock(Category.class);
//
//        when(bankFacade.getCategoryService()).thenReturn(categoryService);
//        doReturn(category).when(categoryService).getByName("Test category");
//        when(category.getId()).thenReturn("2");
//        when(category.getType()).thenReturn(OperationType.INCOME);
//
//        OperationBuilder operationBuilder = new OperationBuilder(bankFacade);
//
//        try {
//            Operation operation = operationBuilder
//                    .setAmount(100)
//                    .setDate("2020-10-10")
//                    .setDescription("Test description")
//                    .setCategoryName("Test category")
//                    .setBankAccountName("Test account")
//                    .build();
//            Operation correctOperation = OperationFactory.create("доход", "1", 100, Constants.DateFormat.parse("2020-10-10"), "Test description", "2");
//            assertThat(operation).usingRecursiveComparison()
//                    .ignoringFields("id")
//                    .isEqualTo(correctOperation);
//        } catch (Exception e) {
//            fail("Expected no exception, but got: " + e.getMessage());
//        }

    //}
}

package org.example.controllers;

import org.apache.commons.lang3.tuple.Pair;
import org.example.domain.BankAccount;
import org.example.domain.Category;
import org.example.domain.CategoryFactory;
import org.example.exceptions.InvalidArgumentException;
import org.example.services.analytics.AnalyticFacade;
import org.example.services.analytics.DifferenceCounterCommand;
import org.example.services.analytics.GroupByCategoryCommand;
import org.example.services.analytics.RecountBalanceCommand;
import org.example.services.domainservices.BankAccountService;
import org.example.services.domainservices.BankFacade;
import org.example.services.domainservices.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnalyticControllerTest {
    @Mock
    AnalyticFacade analyticFacade;
    @Mock
    DifferenceCounterCommand differenceCounterCommand;
    @Mock
    GroupByCategoryCommand groupByCategoryCommand;
    @Mock
    RecountBalanceCommand recountBalanceCommand;
    @Mock
    BankFacade bankFacade;
    //AnalyticController analyticController = new AnalyticController(analyticFacade, bankFacade);

    @InjectMocks
    AnalyticController analyticController;

    @Test
    public void calculateDifferenceTest() {
        //AnalyticController analyticController = new AnalyticController(analyticFacade, bankFacade);
        when(analyticFacade.getDifferenceCounterCommand(true)).thenReturn(differenceCounterCommand);
        when(differenceCounterCommand.execute(Date.valueOf("2020-10-10"), Date.valueOf("2020-10-10"))).thenReturn(100.0);
        try {
        analyticController.CountDifference(Date.valueOf("2020-10-10"), Date.valueOf("2020-10-10"));
        } catch (Exception e) {
            fail("failed test " + e.getMessage());
        }
    }

    @Test
    public void groupByCategoryTest() throws InvalidArgumentException {
        CategoryService categoryService = mock(CategoryService.class);
        Category category = mock(Category.class);
        when(bankFacade.getCategoryService()).thenReturn(categoryService);

        when(analyticFacade.getGroupByCategoryCommand(true)).thenReturn(groupByCategoryCommand);
        Pair<Map<String, Double>, Map<String, Double>> map = Pair.of(new HashMap<String, Double>(), new HashMap<String, Double>());
        map.getLeft().put("12", 100.0);
        map.getRight().put("11", 200.0);
        when(groupByCategoryCommand.execute()).thenReturn(map);

        when(categoryService.findById("12")).thenReturn(category);
        when(category.getName()).thenReturn("max");

        when(categoryService.findById("11")).thenReturn(category);
        when(category.getName()).thenReturn("max");
        analyticController.GroupByCategory();
    }
    @Test
    public void recountBalanceTest() throws InvalidArgumentException {
        BankAccountService bankAccountService = mock(BankAccountService.class);
        BankAccount bankAccount = mock(BankAccount.class);
        when(bankFacade.getBankAccountService()).thenReturn(bankAccountService);        when(analyticFacade.getRecountBalanceService(true)).thenReturn(recountBalanceCommand);
        Map<String, Double> map = new HashMap<>();
        map.put("12", 100.0);
        when(recountBalanceCommand.execute()).thenReturn(map);
        when(bankAccountService.findById("12")).thenReturn(bankAccount);
        when(bankAccount.getName()).thenReturn("max");
        analyticController.RecountBalance();
    }
}

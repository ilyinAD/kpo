package org.example.services.analytics;

import org.example.domain.Category;
import org.example.domain.Operation;
import org.example.domain.OperationType;
import org.example.services.domainservices.BankFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GroupByCategoryCommand implements AnalyticCommandInterface<Pair<Map<String, Double>, Map<String, Double>>>{
    private final BankFacade bankFacade;
    @Autowired
    public GroupByCategoryCommand(BankFacade bankFacade) {
        this.bankFacade = bankFacade;
    }

    @Override
    public Pair<Map<String, Double>, Map<String, Double>> execute(Object... parameters) {
        List<Operation> operations = bankFacade.getOperationService().getAllOperations();
        List<Category> categories = bankFacade.getCategoryService().getAllCategories();

        Map<String, Double> expenses = operations.stream()
                .filter(op -> op.getType().equals(OperationType.EXPENSE))
                .collect(Collectors.groupingBy(
                        op -> categories.stream()
                                .filter(cat -> cat.getId() == op.getCategoryId())
                                .findFirst()
                                .map(Category::getName)
                                .orElse("unknown category"),
                        Collectors.summingDouble(Operation::getAmount)
                ));

        Map<String, Double> incomes = operations.stream()
                .filter(op -> op.getType().equals(OperationType.INCOME))
                .collect(Collectors.groupingBy(
                        op -> categories.stream()
                                .filter(cat -> cat.getId() == op.getCategoryId())
                                .findFirst()
                                .map(Category::getName)
                                .orElse("unknown category"),
                        Collectors.summingDouble(Operation::getAmount)
                ));

        return Pair.of(expenses, incomes);
    }
}

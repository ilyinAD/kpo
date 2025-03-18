package org.example.services.analytics;

import org.example.domain.Category;
import org.example.domain.Operation;
import org.example.domain.OperationType;
import org.example.services.domainservices.BankFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
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

        Map<String, Double> expenses = new HashMap<String, Double>();
        Map<String, Double> incomes = new HashMap<String, Double>();

        for (Operation operation : operations) {
            if (operation.getType().equals(OperationType.INCOME)) {
                incomes.put(operation.getCategoryId(), incomes.getOrDefault(operation.getCategoryId(), 0.0) + operation.getAmount());
            } else {
                expenses.put(operation.getCategoryId(), expenses.getOrDefault(operation.getCategoryId(), 0.0) + operation.getAmount());
            }
        }

        return Pair.of(expenses, incomes);
    }
}

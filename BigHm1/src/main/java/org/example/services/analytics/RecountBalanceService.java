package org.example.services.analytics;

import org.example.domain.BankAccount;
import org.example.domain.Category;
import org.example.domain.Operation;
import org.example.domain.OperationType;
import org.example.services.domainservices.BankFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.util.stream.Collectors;

@Service
public class RecountBalanceService implements AnalyticCommandInterface<Map<Integer, Double>> {
    private final BankFacade bankFacade;
    @Autowired
    public RecountBalanceService(BankFacade bankFacade) {
        this.bankFacade = bankFacade;
    }

    private double GetBalanceByID(int id) {
        List<Operation> operations = bankFacade.getOperationService().getAllOperations();
        double ans = 0;
        for (Operation operation : operations) {
            if (operation.getId() != id) {
                continue;
            }

            if (operation.getType() == OperationType.INCOME) {
                ans += operation.getAmount();
            } else {
                ans -= operation.getAmount();
            }
        }

        return ans;
    }

    @Override
    public Map<Integer, Double> execute(Object... parameters) {
        List<Integer> ids = bankFacade.getBankAccountService().getAllAccounts().stream().map(BankAccount::getId)
                .toList();
        Map<Integer, Double> res = new HashMap<Integer, Double>();
        
        for (Integer id : ids) {
            res.put(id, GetBalanceByID(id));
        }
        
        return res;
    }
}

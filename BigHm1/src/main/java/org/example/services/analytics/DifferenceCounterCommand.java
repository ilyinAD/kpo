package org.example.services.analytics;

import org.example.domain.Operation;
import org.example.domain.OperationType;
import org.example.services.domainservices.BankFacade;
import org.example.services.domainservices.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class DifferenceCounterCommand implements AnalyticCommandInterface<Double>{
    BankFacade bankFacade;
    private Date startDate = new Date();
    private Date endDate = new Date();
    @Autowired
    public DifferenceCounterCommand(BankFacade bankFacade) {
        this.bankFacade = bankFacade;
    }
    @Override
    public Double execute(Object... parameters) throws IllegalArgumentException{
        setParameters(parameters);
        List<Operation> operations = bankFacade.getOperationService().getAllOperations();
        double res = 0;
        for (Operation operation : operations) {
            if (operation.getDate().after(startDate) && operation.getDate().before(endDate)) {
                if (operation.getType() == OperationType.INCOME) {
                    res += operation.getAmount();
                } else {
                    res -= operation.getAmount();
                }
            }
        }

        return res;
    }
    private void setParameters(Object... parameters) throws IllegalArgumentException {
        if (parameters.length == 2 && parameters[0] instanceof Date && parameters[1] instanceof Date) {
            startDate = (Date) parameters[0];
            endDate = (Date) parameters[1];
        } else {
            throw new IllegalArgumentException("Invalid parameters. Expected startDate and endDate of type Date(YYYY-MM-DD)");
        }
    }
}

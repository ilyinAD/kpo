package org.example.controllers;

import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;
import org.example.exceptions.InvalidArgumentException;
import org.example.services.analytics.*;
import org.example.services.domainservices.BankFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Map;
//TODO: add analytic facade шоб крч контроллер принимал фасад и юзал то шо надо
@Controller
public class AnalyticController {
    private final AnalyticFacade analyticFacade;
    @Getter
    private final BankFacade bankFacade;
    @Autowired
    public AnalyticController(AnalyticFacade analyticFacade, BankFacade bankFacade) {
        this.analyticFacade = analyticFacade;
        this.bankFacade = bankFacade;
    }

    public void CountDifference(Date startDate, Date endDate) throws Exception {
        try {
            Double a = analyticFacade.getDifferenceCounterCommand(true).execute(startDate, endDate);
            System.out.println("Посчитанная разница для стартовой даты " + startDate + " и конечной даты " + endDate);
            System.out.println(a);
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }

    public void GroupByCategory() throws InvalidArgumentException {
        Pair<Map<String, Double>, Map<String, Double>> mapPair = analyticFacade.getGroupByCategoryCommand(true).execute();

        System.out.println("Сгруппированные данные: доходы");
        for (Map.Entry<String, Double> entry : mapPair.getRight().entrySet()) {
            System.out.println("Категория " +  bankFacade.getCategoryService().findById(entry.getKey()).getName() + ": " + entry.getValue());
        }
    }

    public void RecountBalance() throws InvalidArgumentException {
        Map<String, Double> map = analyticFacade.getRecountBalanceService(true).execute();

        System.out.println("Пересчетанные балансы для каждого счета");

        for (Map.Entry<String, Double> entry : map.entrySet()) {
            System.out.println("Счет " +  bankFacade.getBankAccountService().findById(entry.getKey()).getName() + ": " + entry.getValue());
        }
    }
}

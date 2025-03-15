package org.example.controllers;

import org.apache.commons.lang3.tuple.Pair;
import org.example.services.analytics.*;
import org.example.services.domainservices.BankFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
//TODO: add analytic facade шоб крч контроллер принимал фасад и юзал то шо надо
@Controller
public class AnalyticController {
    private final AnalyticFacade analyticFacade;
    @Autowired
    public AnalyticController(AnalyticFacade analyticFacade) {
        this.analyticFacade = analyticFacade;
    }

    public void CountDifference(Date startDate, Date endDate) throws Exception {
        try {
            Double a = analyticFacade.getDifferenceCounterCommand(true).execute(startDate, endDate);
            System.out.println(STR."Посчитанная разница для стартовой даты \{startDate} и конечной даты \{endDate}");
            System.out.println(a);
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }

    public void GroupByCategory() {
        Pair<Map<String, Double>, Map<String, Double>> a = analyticFacade.getGroupByCategoryCommand(true).execute();

        System.out.println("Сгруппированные данные доходы и расходы по категориям");
        System.out.println(a);
    }

    public void RecountBalance() {
        Map<String, Double> a = analyticFacade.getRecountBalanceService(true).execute();

        System.out.println("Пересчетанные балансы для каждого счета");
        System.out.println(a);
    }
}

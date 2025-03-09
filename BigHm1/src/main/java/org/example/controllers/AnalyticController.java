package org.example.controllers;

import org.example.services.OperationService;
import org.example.services.analytics.AnalyticCommandInterface;
import org.example.services.analytics.DifferenceCounterCommand;
import org.example.services.analytics.TimeCounterCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Calendar;
import java.util.Date;

@Controller
public class AnalyticController {
    private final OperationService operationService;
    @Autowired
    public AnalyticController(OperationService operationService) {
        this.operationService = operationService;
    }

    public void CountDifference(Date startDate, Date endDate) {
        AnalyticCommandInterface<Integer> mycommand = new TimeCounterCommand<>(new DifferenceCounterCommand(operationService));

        mycommand.setParameters(startDate, endDate);
        Integer a = mycommand.execute();
        System.out.println(a);
    }
}

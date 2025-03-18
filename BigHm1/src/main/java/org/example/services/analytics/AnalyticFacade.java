package org.example.services.analytics;

import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Getter
@Service
public class AnalyticFacade {
    private final DifferenceCounterCommand differenceCounterCommand;
    private final GroupByCategoryCommand groupByCategoryCommand;
    private final RecountBalanceCommand recountBalanceCommand;

    @Autowired
    public AnalyticFacade(DifferenceCounterCommand differenceCounterCommand, GroupByCategoryCommand groupByCategoryCommand, RecountBalanceCommand recountBalanceCommand) {
        this.differenceCounterCommand = differenceCounterCommand;
        this.groupByCategoryCommand = groupByCategoryCommand;
        this.recountBalanceCommand = recountBalanceCommand;
    }

    public AnalyticCommandInterface<Double> getDifferenceCounterCommand(Boolean withTime) {
        if (withTime) {
            return new TimeCounterCommand<>(differenceCounterCommand);
        }

        return differenceCounterCommand;
    }

    public AnalyticCommandInterface<Pair<Map<String, Double>, Map<String, Double>>> getGroupByCategoryCommand(Boolean withTime) {
        if (withTime) {
            return new TimeCounterCommand<>(groupByCategoryCommand);
        }

        return groupByCategoryCommand;
    }

    public AnalyticCommandInterface<Map<String, Double>> getRecountBalanceService(Boolean withTime) {
        if (withTime) {
            return new TimeCounterCommand<>(recountBalanceCommand);
        }

        return recountBalanceCommand;
    }
}

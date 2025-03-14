package org.example.services.analytics;

import org.springframework.stereotype.Service;

public class TimeCounterCommand<T> implements AnalyticCommandInterface<T>{
    private final AnalyticCommandInterface<T> command;

    public TimeCounterCommand(AnalyticCommandInterface<T> command) {
        this.command = command;
    }


    @Override
    public T execute(Object... parameters) {
        long start = System.nanoTime();
        T result = command.execute(parameters);
        long end = System.nanoTime();
        System.out.println(STR."Execution time: \{(end - start) / 1_000_000.0} ms");
        return result;
    }
}

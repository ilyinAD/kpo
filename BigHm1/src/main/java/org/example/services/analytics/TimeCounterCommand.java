package org.example.services.analytics;

import org.springframework.stereotype.Service;

public class TimeCounterCommand<T> implements AnalyticCommandInterface{
    private final AnalyticCommandInterface<T> command;

    public TimeCounterCommand(AnalyticCommandInterface<T> command) {
        this.command = command;
    }


    @Override
    public T execute() {
        long start = System.nanoTime();
        T result = command.execute();
        long end = System.nanoTime();
        System.out.println("Execution time: " + (end - start) / 1_000_000.0 + " ms");
        return result;
    }

    @Override
    public void setParameters(Object... parameters) {
        command.setParameters(parameters);
    }
}

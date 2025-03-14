package org.example.services.analytics;

public interface AnalyticCommandInterface<T> {
    T execute(Object... parameters) throws IllegalArgumentException;
    //void setParameters(Object... parameters);
}

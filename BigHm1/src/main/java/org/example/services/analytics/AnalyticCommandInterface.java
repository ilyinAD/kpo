package org.example.services.analytics;

public interface AnalyticCommandInterface<T> {
    T execute();
    void setParameters(Object... parameters);
}

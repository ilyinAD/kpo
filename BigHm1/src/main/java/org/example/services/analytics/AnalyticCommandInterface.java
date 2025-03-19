package org.example.services.analytics;

import org.example.exceptions.InvalidArgumentException;

public interface AnalyticCommandInterface<T> {
    T execute(Object... parameters);
    //void setParameters(Object... parameters);
}

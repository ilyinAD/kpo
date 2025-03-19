package org.example.interfaces;

import org.example.exceptions.InvalidArgumentException;

import java.util.List;

public interface SaveServiceInterface<T> {
    void addList(List<T> objects) throws InvalidArgumentException;
}

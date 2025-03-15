package org.example.repository;

import org.example.domain.Operation;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class OperationRepository {
    List<Operation> operations = new ArrayList<Operation>();

    public void save(Operation operation) {
        operations.add(operation);
    }

    public List<Operation> findAll() {
        return operations;
    }

    public void delete(String id) {
        operations.removeIf(operation -> Objects.equals(operation.getId(), id));
    }
}

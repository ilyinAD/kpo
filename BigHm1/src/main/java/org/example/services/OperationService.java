package org.example.services;

import org.example.domain.Operation;
import org.example.interfaces.SaveServiceInterface;
import org.example.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationService implements SaveServiceInterface<Operation> {
    OperationRepository operationRepository;
    @Autowired
    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public void AddList(List<Operation> operations) {
        operations.forEach(operationRepository::save);
    }

    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }
}

package org.example.services.domainservices;

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
    public void addList(List<Operation> operations) {
        for (Operation operation : operations) {
            add(operation);
        }
    }
    public void add(Operation operation) {
        operationRepository.save(operation);
    }
    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }
}

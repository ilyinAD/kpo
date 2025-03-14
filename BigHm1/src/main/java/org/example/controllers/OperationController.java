package org.example.controllers;

import org.example.domain.Operation;
import org.example.services.domainservices.OperationService;
import org.springframework.stereotype.Controller;

@Controller
public class OperationController {
    OperationService operationService;
    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }
    public void addOperation(Operation operation) {
        operationService.add(operation);
    }
    public void printAllOperations() {
        operationService.getAllOperations().forEach(System.out::println);
    }
}

package org.example.controllers;

import org.example.services.OperationService;
import org.springframework.stereotype.Controller;

import java.awt.*;

@Controller
public class OperationController {
    OperationService operationService;
    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    public void printAllOperations() {
        operationService.getAllOperations().forEach(System.out::println);
    }
}

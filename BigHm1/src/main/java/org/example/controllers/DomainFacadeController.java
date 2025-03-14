package org.example.controllers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Getter
@Controller
public class DomainFacadeController {
    private final OperationController operationController;
    private final CategoryController categoryController;
    private final BankAccountController bankAccountController;
    private final AnalyticController analyticController;
    @Autowired
    public DomainFacadeController(OperationController operationController, CategoryController categoryController, BankAccountController bankAccountController, AnalyticController analyticController) {
        this.operationController = operationController;
        this.categoryController = categoryController;
        this.bankAccountController = bankAccountController;
        this.analyticController = analyticController;
    }

    public void displayRecountBalance() {
        analyticController.RecountBalance();
    }
    public void displayAll() {
        operationController.printAllOperations();
        categoryController.printAllCategories();
        bankAccountController.printAllAccounts();
    }
}

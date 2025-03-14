package org.example.services.domainservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankFacade {
    private BankAccountService bankAccountService;
    private CategoryService categoryService;
    private OperationService operationService;
    @Autowired
    public BankFacade(BankAccountService bankAccountService, CategoryService categoryService, OperationService operationService) {
        this.bankAccountService = bankAccountService;
        this.categoryService = categoryService;
        this.operationService = operationService;
    }

    public BankAccountService getBankAccountService() {
        return bankAccountService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public OperationService getOperationService() {
        return operationService;
    }
}

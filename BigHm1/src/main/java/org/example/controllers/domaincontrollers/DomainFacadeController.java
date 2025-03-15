package org.example.controllers.domaincontrollers;

import lombok.Getter;
import org.example.controllers.AnalyticController;
import org.example.controllers.domaincontrollers.BankAccountController;
import org.example.controllers.domaincontrollers.CategoryController;
import org.example.controllers.domaincontrollers.OperationController;
import org.example.services.domainservices.BankFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Getter
@Controller
public class DomainFacadeController {
//    private final OperationController operationController;
//    private final CategoryController categoryController;
//    private final BankAccountController bankAccountController;
    private final BankFacade bankFacade;
    @Autowired
    public DomainFacadeController(BankFacade bankFacade) {
        this.bankFacade = bankFacade;
    }

    public void displayBankAccounts() {
        System.out.println(bankFacade.getBankAccountService().getAllAccounts());
    }
    public void displayCategories() {
        System.out.println(bankFacade.getCategoryService().getAllCategories());
    }
    public void displayOperations() {
        System.out.println(bankFacade.getOperationService().getAllOperations());
    }
    public void displayAll() {
        displayBankAccounts();
        displayCategories();
        displayOperations();
    }
}

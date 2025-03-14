package org.example.controllers;

import org.example.domain.BankAccount;
import org.example.services.domainservices.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BankAccountController {
    private final BankAccountService bankAccountService;
    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    public void addAccount(BankAccount account) {
        bankAccountService.add(account);
    }
    public void printAllAccounts() {
        bankAccountService.getAllAccounts().forEach(System.out::println);
    }
}

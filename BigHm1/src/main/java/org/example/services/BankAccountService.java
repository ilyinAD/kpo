package org.example.services;

import org.example.domain.BankAccount;
import org.example.interfaces.SaveServiceInterface;
import org.example.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService implements SaveServiceInterface<BankAccount> {

    private final BankAccountRepository repository;
    @Autowired
    public BankAccountService(BankAccountRepository repository ) {
        this.repository = repository;
    }

    public void AddList(List<BankAccount> accounts) {
        accounts.forEach(repository::save);
    }
    public List<BankAccount> getAllAccounts() {
        return repository.findAll();
    }
}

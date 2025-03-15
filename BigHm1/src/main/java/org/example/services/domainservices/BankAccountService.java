package org.example.services.domainservices;

import org.example.domain.BankAccount;
import org.example.exceptions.InvalidArgumentException;
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

    public void addList(List<BankAccount> accounts) {
        accounts.forEach(repository::save);
    }

    public void add(BankAccount account) throws InvalidArgumentException {
        boolean isNotUnique = repository.findAll().stream()
                .anyMatch(repAccount -> repAccount.getName().equals(account.getName()));
        if (isNotUnique) {
            throw new InvalidArgumentException("Account with this name already exists");
        }
        repository.save(account);
    }

    public String getIdByName(String name) throws InvalidArgumentException {
        BankAccount account = repository.findAll().stream()
               .filter(repAccount -> repAccount.getName().equals(name))
               .findFirst()
               .orElseThrow(() -> new InvalidArgumentException("Account with this name does not exist"));
        return account.getId();
    }
    public List<BankAccount> getAllAccounts() {
        return repository.findAll();
    }
}

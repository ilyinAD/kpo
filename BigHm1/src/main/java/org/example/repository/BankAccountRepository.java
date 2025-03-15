package org.example.repository;

import org.example.domain.BankAccount;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class BankAccountRepository {
    private final List<BankAccount> accounts = new ArrayList<>();

    public void save(BankAccount account) {
        accounts.add(account);
    }
    public List<BankAccount> findAll() {
        return accounts;
    }
    public void delete(String id) {
        accounts.removeIf(account -> Objects.equals(account.getId(), id));
    }
}

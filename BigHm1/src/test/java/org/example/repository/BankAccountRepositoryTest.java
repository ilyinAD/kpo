package org.example.repository;

import org.example.builders.BankAccountBuilder;
import org.example.domain.BankAccount;
import org.example.domain.BankAccountFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankAccountRepositoryTest {
    BankAccountRepository bankAccountRepository = new BankAccountRepository();

    @Test
    void saveAccountTest() {
        BankAccount account = new BankAccountBuilder()
                .setName("artem")
                .setBalance(1000)
                .build();
        bankAccountRepository.save(account);
    }

    @Test
    void findAllTest() {
        BankAccount account = new BankAccountBuilder()
                .setName("artem")
                .setBalance(1000)
                .build();
        bankAccountRepository.save(account);

        BankAccount account1 = new BankAccountBuilder()
                .setName("max")
                .setBalance(2000)
                .build();
        bankAccountRepository.save(account1);

        List<BankAccount> accounts = bankAccountRepository.findAll();
        List<BankAccount> expectAccounts = new ArrayList<BankAccount>();
        expectAccounts.add(account);
        expectAccounts.add(account1);
        assertEquals(accounts, expectAccounts);
    }

    @Test
    void deleteAccountTest() {
        BankAccount account = BankAccountFactory.create("artem", 1000);
        bankAccountRepository.save(account);
        bankAccountRepository.delete(account.getId());

        List<BankAccount> accounts = bankAccountRepository.findAll();
        assertTrue(accounts.isEmpty());
    }
}

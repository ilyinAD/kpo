package org.example.services.domainservices;

import org.example.domain.BankAccount;
import org.example.domain.BankAccountFactory;
import org.example.exceptions.InvalidArgumentException;
import org.example.repository.BankAccountRepository;
import org.example.services.domainservices.BankAccountService;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class BankAccountServiceTest {


    private BankAccountService service = new BankAccountService(new BankAccountRepository());

    private List<BankAccount> accounts;


    @Test
    public void AddListOfAccountsWithoutDuplicates() {
        accounts = Arrays.asList(
                BankAccountFactory.create("account1", 100),
                BankAccountFactory.create("account2", 100),
                BankAccountFactory.create("account3", 100)
        );
        try {
            service.addList(accounts);
            assertEquals(service.getAllAccounts(), accounts);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void AddListOfAccountsWithDuplicate() {
        accounts = Arrays.asList(
                BankAccountFactory.create("account1", 100),
                BankAccountFactory.create("account2", 100),
                BankAccountFactory.create("account1", 100)
        );
        assertThrows(InvalidArgumentException.class, () -> service.addList(accounts));
    }

    @Test
    public void FindExistAccountByName() {
        accounts = Arrays.asList(
                BankAccountFactory.create("account1", 100),
                BankAccountFactory.create("account2", 100),
                BankAccountFactory.create("account3", 100)
        );
        try {
            service.addList(accounts);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        try {
            BankAccount account = service.getByName("account2");
            assertEquals(account, accounts.get(1));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void FindNonExistAccountByName() {
        accounts = Arrays.asList(
                BankAccountFactory.create("account1", 100),
                BankAccountFactory.create("account2", 100),
                BankAccountFactory.create("account3", 100)
        );
        try {
            service.addList(accounts);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertThrows(InvalidArgumentException.class, () -> service.getByName("account"));
    }

    @Test
    public void FindExistAccountById() {
        accounts = Arrays.asList(
                BankAccountFactory.create("account1", 100),
                BankAccountFactory.create("account2", 100),
                BankAccountFactory.create("account3", 100)
        );
        try {
            service.addList(accounts);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        try {
            BankAccount account = service.findById(service.getByName("account2").getId());
            assertEquals(account, accounts.get(1));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void FindNonExistAccountById() {
        accounts = Arrays.asList(
                BankAccountFactory.create("account1", 100),
                BankAccountFactory.create("account2", 100),
                BankAccountFactory.create("account3", 100)
        );
        try {
            service.addList(accounts);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertThrows(InvalidArgumentException.class, () -> service.findById("incorrect id"));
    }

    @Test
    public void GetAllAccounts() {
        accounts = Arrays.asList(
                BankAccountFactory.create("account1", 100),
                BankAccountFactory.create("account2", 100),
                BankAccountFactory.create("account3", 100)
        );

        try {
            service.addList(accounts);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(accounts, service.getAllAccounts());
    }
}
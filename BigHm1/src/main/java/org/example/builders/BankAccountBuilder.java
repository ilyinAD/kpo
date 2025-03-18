package org.example.builders;

import org.example.domain.BankAccount;
import org.example.domain.BankAccountFactory;

public class BankAccountBuilder {
    private String name;
    private double balance;

    public BankAccountBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BankAccountBuilder setBalance(double balance) {
        this.balance = balance;
        return this;
    }

    public BankAccount build() {
        return BankAccountFactory.create(name, balance);
    }
}

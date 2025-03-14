package org.example.builders;

import org.example.domain.BankAccount;

public class BankAccountBuilder {
    private int id;
    private String name;
    private double balance;

    public BankAccountBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public BankAccountBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BankAccountBuilder setBalance(double balance) {
        this.balance = balance;
        return this;
    }

    public BankAccount build() {
        return BankAccount.create(id, name, balance);
    }
}

package org.example.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class BankAccount {
    private int id;

    private String name;

    private double balance;

    BankAccount(int id, String name, double balance) {
        System.out.println("private constructor");
        this.id = id;
        this.name = name;
        this.balance = balance;
    }
    @JsonCreator
    public static BankAccount create(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("balance") double balance) {
        System.out.println("static create");
        return new BankAccount(id, name, balance);
    }

//    public static BankAccount create(int id, String name, double balance) {
//        System.out.println("static create");
//        return new BankAccount(id, name, balance);
//    }

    @Override
    public String toString() {
        return STR."BankAccount{id=\{id}, name='\{name}\{'\''}, balance=\{balance}\{'}'}";
    }
}

package org.example.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import java.util.logging.Logger;

@Getter
public class BankAccount {
    private static final Logger logger = Logger.getLogger(BankAccount.class.getName());
    private String id;

    private String name;

    private double balance;

    BankAccount(String id, String name, double balance) {
        logger.info("private constructor bankaccount");
        this.id = id;
        this.name = name;
        this.balance = balance;
    }
//    private static BankAccount create(String name, double balance) {
//        System.out.println("static create");
//        String id = UUID.randomUUID().toString();
//        return new BankAccount(id, name, balance);
//    }

    @JsonCreator
    private static BankAccount create(@JsonProperty("id") String id, @JsonProperty("name") String name, @JsonProperty("balance") double balance) {
        logger.info("jackson static create bankaccount");

        return BankAccountFactory.create(id, name, balance);
    }

//    public static BankAccount create(int id, String name, double balance) {
//        System.out.println("static create");
//        return new BankAccount(id, name, balance);
//    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}

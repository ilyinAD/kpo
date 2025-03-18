package org.example.domain;

import java.util.UUID;
import java.util.logging.Logger;

public class BankAccountFactory {
    private static final Logger logger = Logger.getLogger(BankAccountFactory.class.getName());
    public static BankAccount create(String name, double balance) {
        logger.info("fabric create bankaccount");
        String id = UUID.randomUUID().toString();
        return create(id, name, balance);
    }

    static BankAccount create(String id, String name, double balance) {
        logger.info("fabric final create bankaccount");
        return new BankAccount(id, name, balance);
    }
}

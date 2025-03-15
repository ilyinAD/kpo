package org.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.example.exceptions.InvalidArgumentException;

import java.util.Date;
import java.util.UUID;

@Getter
public class Operation {
    private final String id;

    private final OperationType type;

    private final String bankAccountId;

    private final double amount;

    private final Date date;

    private final String description;

    private final String categoryId;

    private Operation(String id, OperationType type, String bankAccountId, double amount, Date date, String description, String categoryId) {
        if (amount < 0) throw new IllegalArgumentException("Amount can't be negative");

        this.id = id;
        this.type = type;
        this.bankAccountId = bankAccountId;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.categoryId = categoryId;
    }

    public static Operation create(String type, String bankAccountId,
                                   double amount, Date date, String description,
                                   String categoryId) throws InvalidArgumentException {
        if (amount < 0) throw new InvalidArgumentException("Amount can't be negative");
        OperationType operationType = OperationType.fromString(type);
        if (operationType == null) {
            throw new InvalidArgumentException("Invalid operation type: " + type);
        }

        String id = UUID.randomUUID().toString();

        return new Operation(id, operationType, bankAccountId, amount, date, description, categoryId);
    }

    @JsonCreator
    public static Operation create(@JsonProperty("id") String id, @JsonProperty("type") String type, @JsonProperty("bankAccountId") String bankAccountId,
                                   @JsonProperty("amount") double amount, @JsonProperty("date") Date date, @JsonProperty("description") String description,
                                   @JsonProperty("categoryId") String categoryId) throws InvalidArgumentException {
        if (amount < 0) throw new InvalidArgumentException("Amount can't be negative");
        OperationType operationType = OperationType.fromString(type);
        if (operationType == null) {
            throw new InvalidArgumentException("Invalid operation type: " + type);
        }

        return new Operation(id, operationType, bankAccountId, amount, date, description, categoryId);
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", bankAccountId='" + bankAccountId + '\'' +
                ", amount=" + amount + '\'' +
                ", date=" + date + '\'' +
                ", description='" + description + '\'' +
                ", categoryId='" + categoryId + '\'' +
                '}';
        //return "Operation{id=\{id}, type='\{type}\{'\''}, bankAccountId=\{bankAccountId}, amount=\{amount}, date=\{date}, description='\{description}\{'\''}, categoryId=\{categoryId}\{'}'}";
    }
}

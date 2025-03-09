package org.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public class Operation {
    private final int id;

    private final OperationType type;

    private final int bankAccountId;

    private final double amount;

    private final Date date;

    private final String description;

    private final int categoryId;

    private Operation(int id, OperationType type, int bankAccountId, double amount, Date date, String description, int categoryId) {
        if (amount < 0) throw new IllegalArgumentException("Amount can't be negative");

        this.id = id;
        this.type = type;
        this.bankAccountId = bankAccountId;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.categoryId = categoryId;
    }

    @JsonCreator
    public static Operation create(@JsonProperty("id") int id, @JsonProperty("type") String type, @JsonProperty("bankAccountId") int bankAccountId,
                                   @JsonProperty("amount") double amount, @JsonProperty("date") Date date, @JsonProperty("description") String description,
                                   @JsonProperty("categoryId") int categoryId) {
        if (amount < 0) throw new IllegalArgumentException("Amount can't be negative");
        OperationType operationType = OperationType.fromString(type);
        if (operationType == null) {
            throw new IllegalArgumentException("Invalid operation type: " + type);
        }

        return new Operation(id, operationType, bankAccountId, amount, date, description, categoryId);
    }

    @Override
    public String toString() {
        return STR."Operation{id=\{id}, type='\{type}\{'\''}, bankAccountId=\{bankAccountId}, amount=\{amount}, date=\{date}, description='\{description}\{'\''}, categoryId=\{categoryId}\{'}'}";
    }
}

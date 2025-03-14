package org.example.builders;

import org.example.domain.Operation;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

public class OperationBuilder {
    private int id;
    private String type;
    private int bankAccountId;
    private double amount;
    private Date date;
    private String description = "Нет описания";  // значение по умолчанию
    private int categoryId;

    public OperationBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public OperationBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public OperationBuilder setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
        return this;
    }

    public OperationBuilder setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public OperationBuilder setDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.date = sdf.parse(date);

        return this;
    }

    public OperationBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public OperationBuilder setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public Operation build() {
        return Operation.create(id, type, bankAccountId, amount, date, description, categoryId);
    }
}

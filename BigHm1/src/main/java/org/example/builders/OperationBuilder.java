package org.example.builders;

import org.example.constants.Constants;
import org.example.domain.Operation;
import org.example.domain.OperationFactory;
import org.example.exceptions.InvalidArgumentException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

public class OperationBuilder {
    private String type;
    private String bankAccountId;
    private double amount;
    private Date date;
    private String description = "Нет описания";  // значение по умолчанию
    private String categoryId;

    public OperationBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public OperationBuilder setBankAccountId(String bankAccountId) {
        this.bankAccountId = bankAccountId;
        return this;
    }

    public OperationBuilder setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public OperationBuilder setDate(String date) throws ParseException {
        this.date = Constants.DateFormat.parse(date);

        return this;
    }

    public OperationBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public OperationBuilder setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public Operation build() throws InvalidArgumentException {
        return OperationFactory.create(type, bankAccountId, amount, date, description, categoryId);
    }
}

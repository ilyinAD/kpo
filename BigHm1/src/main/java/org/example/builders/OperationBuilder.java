package org.example.builders;

import org.example.constants.Constants;
import org.example.controllers.DomainFacadeController;
import org.example.domain.Category;
import org.example.domain.Operation;
import org.example.domain.OperationFactory;
import org.example.exceptions.InvalidArgumentException;
import org.example.services.domainservices.BankFacade;

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
    private final BankFacade bankFacade;

    public OperationBuilder(BankFacade bankFacade) {
        this.bankFacade = bankFacade;
    }

    private OperationBuilder setBankAccountId(String bankAccountId) {
        this.bankAccountId = bankAccountId;
        return this;
    }

    public OperationBuilder setBankAccountName(String bankAccountName) throws InvalidArgumentException {
        return setBankAccountId(bankFacade.
                getBankAccountService().getByName(bankAccountName).getId());
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

    private OperationBuilder setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }
    public OperationBuilder setCategoryName(String categoryName) throws InvalidArgumentException {
//        String id = bankFacade.
//                getCategoryService().getByName(categoryName).getId();

        Category category = bankFacade.getCategoryService().getByName(categoryName);

        this.type = category.getType().toString();

        return setCategoryId(category.getId());
    }

    public Operation build() throws InvalidArgumentException {
        return OperationFactory.create(type, bankAccountId, amount, date, description, categoryId);
    }
}

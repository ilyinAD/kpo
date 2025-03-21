package org.example.services.importer;

import org.example.domain.BankAccount;
import org.example.domain.Category;
import org.example.domain.Operation;
import org.example.exceptions.InvalidArgumentException;
import org.example.interfaces.SaveServiceInterface;
import org.example.services.domainservices.BankAccountService;
import org.example.services.domainservices.CategoryService;
import org.example.services.domainservices.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
@Service
abstract class ImportData {
    SaveServiceInterface<BankAccount> bankAccountService;
    SaveServiceInterface<Category> categoryService;
    SaveServiceInterface<Operation> operationService;
    @Autowired
    public ImportData(SaveServiceInterface<BankAccount> bankAccountService, SaveServiceInterface<Category> categoryService, SaveServiceInterface<Operation> operationService) {
        this.bankAccountService = bankAccountService;
        this.categoryService = categoryService;
        this.operationService = operationService;
    }
    public void importData(String filePathAccounts, String filePathCategories, String filePathOperations) throws IOException, InvalidArgumentException {
        importAccounts(filePathAccounts);
        importCategories(filePathCategories);
        importOperations(filePathOperations);
    }

    private void importAccounts(String filePath) throws IOException, InvalidArgumentException {
        String data = readData(filePath);
        List<BankAccount> accounts = parseData(data,  BankAccount.class);
        saveData(accounts, bankAccountService);
    }

    private void importCategories(String filePath) throws IOException, InvalidArgumentException {
        String data = readData(filePath);
        List<Category> categories = parseData(data,  Category.class);
        saveData(categories, categoryService);
    }

    private void importOperations(String filePath) throws IOException, InvalidArgumentException {
        String data = readData(filePath);
        List<Operation> operations = parseData(data,  Operation.class);
        saveData(operations, operationService);
    }

    public <T> void saveData(List<T> data, SaveServiceInterface<T> service) throws InvalidArgumentException {
        service.addList(data);
    }
    public abstract <T> List<T> parseData(String data, Class<T> type) throws IOException;
    public String readData(String filePath) throws IOException {
        return Files.readString(Paths.get(filePath));
    }
}

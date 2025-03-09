package org.example.services.exporter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.example.domain.BankAccount;
import org.example.domain.Category;
import org.example.domain.Operation;
import org.example.services.BankAccountService;
import org.example.services.CategoryService;
import org.example.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Service
public class ExportDataService {
    BankAccountService bankAccountService;
    CategoryService categoryService;
    OperationService operationService;
    @Autowired
    public ExportDataService(BankAccountService bankAccountService, CategoryService categoryService, OperationService operationService) {
        this.bankAccountService = bankAccountService;
        this.categoryService = categoryService;
        this.operationService = operationService;
    }
    public void Export(String filePathAccounts, String filePathCategories, String filePathOperations, String fileType) throws IOException {
        switch (fileType) {
            case "json":
                writeJson(bankAccountService.getAllAccounts(), BankAccount.class, filePathAccounts);
                writeJson(categoryService.getAllCategories(), Category.class, filePathCategories);
                writeJson(operationService.getAllOperations(), Operation.class, filePathOperations);
                break;
            case "yaml":
                writeYaml(bankAccountService.getAllAccounts(), BankAccount.class, filePathAccounts);
                writeYaml(categoryService.getAllCategories(), Category.class, filePathCategories);
                writeYaml(operationService.getAllOperations(), Operation.class, filePathOperations);
                break;
            case "csv":
                writeCsv(bankAccountService.getAllAccounts(), BankAccount.class, filePathAccounts);
                writeCsv(categoryService.getAllCategories(), Category.class, filePathCategories);
                writeCsv(operationService.getAllOperations(), Operation.class, filePathOperations);
                break;
            default:
                throw new IllegalArgumentException("Unsupported file type");
        }

    }
    private <T> void writeJson(List<T> dataList, Class<T> type, String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(jsonFilePath), dataList);
    }
    private <T> void writeYaml(List<T> dataList, Class<T> type, String yamlFilePath) throws IOException {
        ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
        yamlMapper.writeValue(new File(yamlFilePath), dataList);
    }
    private <T> void writeCsv(List<T> dataList, Class<T> type, String csvFilePath) throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = csvMapper.schemaFor(type).withHeader().withColumnReordering(true);
        csvMapper.writerFor(List.class).with(csvSchema).writeValue(new File(csvFilePath), dataList);
    }
}

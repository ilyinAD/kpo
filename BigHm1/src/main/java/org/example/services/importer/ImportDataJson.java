package org.example.services.importer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.example.services.domainservices.BankAccountService;
import org.example.services.domainservices.CategoryService;
import org.example.services.domainservices.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class ImportDataJson extends ImportData {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public ImportDataJson(BankAccountService bankAccountService, CategoryService categoryService, OperationService operationService) {
        super(bankAccountService, categoryService, operationService);
    }

    @Override
    public <T> List<T> parseData(String data, Class<T> type) throws IOException {
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, type);
        return objectMapper.readValue(
                data,
                listType
        );
    }
}

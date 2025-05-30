package org.example.services.importer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.services.domainservices.BankAccountService;
import org.example.services.domainservices.CategoryService;
import org.example.services.domainservices.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
@Service
public class ImportDataYaml extends ImportData {
    @Autowired
    public ImportDataYaml(BankAccountService bankAccountService, CategoryService categoryService, OperationService operationService) {
        super(bankAccountService, categoryService, operationService);
    }

    @Override
    public <T> List<T> parseData(String data, Class<T> type) throws IOException {
        if (data == null || data.isEmpty()) {
            return Collections.emptyList();
        }

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(data, objectMapper.getTypeFactory().constructCollectionType(List.class, type));
    }
}

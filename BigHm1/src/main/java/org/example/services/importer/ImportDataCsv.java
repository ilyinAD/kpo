package org.example.services.importer;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.example.services.domainservices.BankAccountService;
import org.example.services.domainservices.CategoryService;
import org.example.services.domainservices.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
@Service
public class ImportDataCsv extends ImportData{
    private final CsvMapper csvMapper = new CsvMapper();
    @Autowired
    public ImportDataCsv(BankAccountService bankAccountService, CategoryService categoryService, OperationService operationService) {
        super(bankAccountService, categoryService, operationService);
    }
    @Override
    public <T> List<T> parseData(String data, Class<T> type) throws IOException {
        if (data == null || data.isEmpty()) {
            return Collections.emptyList();
        }

        CsvSchema csvSchema = csvMapper.schemaFor(type).withHeader().withColumnReordering(true);

        MappingIterator<T> dataIterator = csvMapper.readerFor(type)
                .with(csvSchema)
                .readValues(data);

        return dataIterator.readAll();
    }
}

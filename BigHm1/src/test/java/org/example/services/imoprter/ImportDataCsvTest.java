package org.example.services.imoprter;

import org.example.repository.BankAccountRepository;
import org.example.repository.CategoryRepository;
import org.example.repository.OperationRepository;
import org.example.services.domainservices.BankAccountService;
import org.example.services.domainservices.CategoryService;
import org.example.services.domainservices.OperationService;
import org.example.services.importer.ImportDataCsv;
import org.example.services.importer.ImportDataJson;
import org.example.services.importer.ImportDataYaml;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Fail.fail;

public class ImportDataCsvTest {
    BankAccountService bankAccountService = new BankAccountService(new BankAccountRepository());
    CategoryService categoryService = new CategoryService(new CategoryRepository());
    OperationService operationService = new OperationService(new OperationRepository());
    ImportDataCsv importDataCsv = new ImportDataCsv(bankAccountService, categoryService, operationService);
    ImportDataYaml importDataYaml = new ImportDataYaml(bankAccountService, categoryService, operationService);


    @Test
    public void importCsvTest() {
        try {
            importDataCsv.importData("src/test/java/datacsv/bankAccounts.csv","src/test/java/datacsv/categories.csv", "src/test/java/datacsv/operations.csv");
        } catch (Exception e) {
            fail("Test failed " + e.getMessage());
        }
    }

    @Test
    public void importJsonTest() {
        try {
            importDataCsv.importData("src/test/java/datajson/bankAccounts.json","src/test/java/datajson/categories.json", "src/test/java/datajson/operations.json");
        } catch (Exception e) {
            fail("Test failed " + e.getMessage());
        }
    }

    @Test
    public void importYamlTest() {
        try {
            importDataYaml.importData("src/test/java/datayaml/bankAccounts.yaml","src/test/java/datayaml/categories.yaml", "src/test/java/datayaml/operations.yaml");
        } catch (Exception e) {
            fail("Test failed " + e.getMessage());
        }
    }
}

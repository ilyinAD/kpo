package org.example;

import org.example.controllers.AnalyticController;
import org.example.controllers.BankAccountController;
import org.example.controllers.CategoryController;
import org.example.controllers.OperationController;
import org.example.domain.OperationType;
import org.example.services.OperationService;
import org.example.services.analytics.AnalyticCommandInterface;
import org.example.services.analytics.DifferenceCounterCommand;
import org.example.services.analytics.TimeCounterCommand;
import org.example.services.exporter.ExportDataService;
import org.example.services.importer.ImportDataCsv;
import org.example.services.importer.ImportDataJson;
import org.example.services.importer.ImportDataYaml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

@SpringBootApplication
public class Main implements CommandLineRunner {
    ImportDataJson importDataJson;
    ImportDataYaml importDataYaml;
    ImportDataCsv importDataCsv;
    ExportDataService exportDataService;
    BankAccountController bankAccountController;
    CategoryController categoryController;
    OperationController operationController;

    AnalyticController analyticsController;
    @Autowired
    public Main(ImportDataJson importDataJson, ImportDataYaml importDataYaml, ImportDataCsv importDataCsv,
                ExportDataService exportDataService, BankAccountController bankAccountController, AnalyticController analyticsController,
                CategoryController categoryController, OperationController operationController) {
        this.importDataJson = importDataJson;
        this.importDataYaml = importDataYaml;
        this.importDataCsv = importDataCsv;
        this.exportDataService = exportDataService;
        this.bankAccountController = bankAccountController;
        this.categoryController = categoryController;
        this.operationController = operationController;
        this.analyticsController = analyticsController;
    }
    @Override
    public void run(String... args) throws Exception{
//        try {
//            OperationType t = OperationType.fromString("доход");
//            System.out.println(t);
//        } catch (Exception e) {
//            System.out.println("ERROR");
//            System.out.println(e.getMessage());
//        }



        try {
            //importDataCsv.importData("datacsv\\bankAccounts.csv", "datacsv\\categories.csv", "datacsv\\operations.csv");
            importDataJson.importData("datajson\\bankAccounts.json", "datajson\\categories.json", "datajson\\operations.json");
            //importDataYaml.importData("datacsv\\bankAccounts.csv", "datacsv\\categories.csv", "datacsv\\operations.csv");

            bankAccountController.printAllAccounts();
            categoryController.printAllCategories();
            operationController.printAllOperations();
        } catch (Exception e) {
            System.out.println("ERROR");
            System.out.println(e.getMessage());
        }
        Calendar calendar = Calendar.getInstance();

        calendar.set(2021, Calendar.MARCH, 9, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date date1 = calendar.getTime();

        calendar.set(2025, Calendar.MARCH, 9, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date date2 = calendar.getTime();
        analyticsController.CountDifference(date1, date2);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    bankAccountController.printAllAccounts();
                    break;
                case 2:
                    categoryController.printAllCategories();
                    break;
                case 3:
                    operationController.printAllOperations();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (choice == 5) {
                break;
            }
        }

        exportDataService.Export("datacsv\\bankAccounts.csv", "datacsv\\categories.csv", "datacsv\\operations.csv", "csv");

    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
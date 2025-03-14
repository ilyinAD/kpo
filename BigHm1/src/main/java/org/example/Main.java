package org.example;

import org.example.controllers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//TODO: крч добавить шоб айдишник генерировался сам при создании(например передавать функцию генерации в create). Мб сделать проверку на то что foreign id существует

@SpringBootApplication
public class Main implements CommandLineRunner {
//    ImportDataJson importDataJson;
////    ImportDataYaml importDataYaml;
////    ImportDataCsv importDataCsv;
////    ExportDataService exportDataService;
////    BankAccountController bankAccountController;
////    CategoryController categoryController;
////    OperationController operationController;
////
////    AnalyticController analyticsController;

    private final TerminalController terminalController;
    @Autowired
    public Main(TerminalController terminalController) {
        this.terminalController = terminalController;
//        this.importDataJson = importDataJson;
//        this.importDataYaml = importDataYaml;
//        this.importDataCsv = importDataCsv;
//        this.exportDataService = exportDataService;
//        this.bankAccountController = bankAccountController;
//        this.categoryController = categoryController;
//        this.operationController = operationController;
//        this.analyticsController = analyticsController;
    }
    @Override
    public void run(String... args) throws Exception{
        terminalController.start();
//        try {
//            //importDataCsv.importData("datacsv\\bankAccounts.csv", "datacsv\\categories.csv", "datacsv\\operations.csv");
//            importDataJson.importData("datajson\\bankAccounts.json", "datajson\\categories.json", "datajson\\operations.json");
//            //importDataYaml.importData("datacsv\\bankAccounts.csv", "datacsv\\categories.csv", "datacsv\\operations.csv");
//
//            bankAccountController.printAllAccounts();
//            categoryController.printAllCategories();
//            operationController.printAllOperations();
//        } catch (Exception e) {
//            System.out.println("ERROR");
//            System.out.println(e.getMessage());
//        }
//        Calendar calendar = Calendar.getInstance();
//
//        calendar.set(2021, Calendar.MARCH, 9, 0, 0, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//
//        Date date1 = calendar.getTime();
//
//        calendar.set(2025, Calendar.MARCH, 9, 0, 0, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//
//        Date date2 = calendar.getTime();
//        analyticsController.CountDifference(date1, date2);
//        analyticsController.GroupByCategory();


    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
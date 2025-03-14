package org.example.controllers;

import org.example.builders.BankAccountBuilder;
import org.example.builders.CategoryBuilder;
import org.example.builders.OperationBuilder;
import org.example.services.exporter.ExportDataService;
import org.example.services.importer.ImportDataJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
@Controller
public class TerminalController {
    Scanner scanner;
    private final DomainFacadeController domainFacadeController;
    private final AnalyticController analyticController;
    private final ImportDataJson importData;
    private final ExportDataService exportData;
    @Autowired
    TerminalController(DomainFacadeController displayController, ImportDataJson importData, ExportDataService exportData, AnalyticController analyticController) {
        this.scanner = new Scanner(System.in);
        this.domainFacadeController = displayController;
        this.importData = importData;
        this.exportData = exportData;
        this.analyticController = analyticController;
    }

    void displayInfo() {
        System.out.println("\nВыберите действие:");
        System.out.println("1 - Добавить счет");
        System.out.println("2 - Добавить категорию");
        System.out.println("3 - Добавить операцию");
        System.out.println("4 - Показать все данные");
        System.out.println("5 - Пересчитать баланс аккаунтов");
        System.out.println("0 - Выход");
        System.out.print("Введите номер действия: ");
    }
    public void start() {

        try {
            importData.importData("datajson\\bankAccounts.json", "datajson\\categories.json", "datajson\\operations.json");
        }
        catch (Exception e) {
            System.out.println(STR."Ошибка импорта данных: \{e.getMessage()}");
            return;
        }

        while (true) {
            displayInfo();
            int choice;
            String input = scanner.nextLine();

            try {
                choice = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Некорректный ввод. Пожалуйста, введите число.");
                continue;
            }


            switch (choice) {
                case 1:
                    try {
                        addBankAccount();
                    } catch (InputMismatchException e) {
                        scanner.nextLine();
                        System.out.println(STR."Ошибка добавления счета: \{e.getMessage()}");
                    } catch (Exception e) {
                        System.out.println(STR."Ошибка добавления счета: \{e.getMessage()}");
                    }

                    break;
                case 2:
                    try {
                        addCategory();
                    } catch (InputMismatchException e) {
                        scanner.nextLine();
                        System.out.println(STR."Ошибка добавления категории: \{e.getMessage()}");
                    } catch (Exception e) {
                        System.out.println(STR."Ошибка добавления категории: \{e.getMessage()}");
                    }

                    break;
                case 3:
                    try {
                        addOperation();
                    } catch (InputMismatchException e) {
                        scanner.nextLine();
                        System.out.println(STR."Ошибка добавления операции: \{e.getMessage()}");
                    } catch (Exception e) {
                        System.out.println(STR."Ошибка добавления операции: \{e.getMessage()}");
                    }

                    break;
                case 4:
                    try {
                        displayData();
                    } catch (Exception e) {
                        System.out.println(STR."Ошибка показа всех данных: \{e.getMessage()}");
                    }

                    break;
                case 5:
                    analyticController.RecountBalance();
                    break;
                case 0:
                    System.out.println("Выход из программы...");
                    break;

                default:
                    System.out.println("Некорректный ввод, попробуйте снова.");
            }

            if (choice == 0) {
                break;
            }
        }

        try {
            exportData.Export("datacsv\\bankAccounts.csv", "datacsv\\categories.csv", "datacsv\\operations.csv", "csv");
        } catch (Exception e) {
            System.out.println(STR."Ошибка экспорта данных: \{e.getMessage()}");
        }

    }

    private void addBankAccount() {
        BankAccountBuilder builder = new BankAccountBuilder();

        System.out.print("Введите ID счета: ");
        builder = builder.setId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Введите название счета: ");
        builder = builder.setName(scanner.nextLine());

        System.out.print("Введите баланс счета: ");
        builder = builder.setBalance(scanner.nextDouble());
        scanner.nextLine();

        domainFacadeController.getBankAccountController().addAccount(builder.build());
        System.out.println("Счет добавлен!");
    }

    private void addCategory() {
        CategoryBuilder builder = new CategoryBuilder();

        System.out.print("Введите ID категории: ");
        builder = builder.setId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Введите тип категории (доход/расход): ");
        builder = builder.setType(scanner.nextLine().toLowerCase());

        System.out.print("Введите название категории: ");
        String name = scanner.nextLine();

        domainFacadeController.getCategoryController().addCategory(builder.build());
        System.out.println("Категория добавлена!");
    }

    private void addOperation() throws ParseException {
        OperationBuilder builder = new OperationBuilder();

        System.out.print("Введите ID операции: ");
        builder = builder.setId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Введите тип операции (доход/расход): ");
        builder = builder.setType(scanner.nextLine().toLowerCase());

        System.out.print("Введите ID банковского счета: ");
        builder = builder.setBankAccountId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Введите сумму: ");
        builder = builder.setAmount(scanner.nextDouble());
        scanner.nextLine();

        System.out.print("Введите дату (YYYY-MM-DD): ");
        builder = builder.setDate(scanner.nextLine());

        System.out.print("Введите описание: ");
        builder = builder.setDescription(scanner.nextLine());

        System.out.print("Введите ID категории: ");
        builder = builder.setCategoryId(scanner.nextInt());
        scanner.nextLine();

        domainFacadeController.getOperationController().addOperation(builder.build());
        System.out.println("Операция добавлена!");
    }

    private void displayData() {
        domainFacadeController.displayAll();
    }
}

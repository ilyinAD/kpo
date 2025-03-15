package org.example.controllers;

import org.example.builders.BankAccountBuilder;
import org.example.builders.CategoryBuilder;
import org.example.builders.OperationBuilder;
import org.example.constants.Constants;
import org.example.controllers.domaincontrollers.DomainFacadeController;
import org.example.domain.Category;
import org.example.exceptions.InvalidArgumentException;
import org.example.services.exporter.ExportDataService;
import org.example.services.importer.ImportDataCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
@Controller
public class TerminalController {
    Scanner scanner;
    private final DomainFacadeController domainFacadeController;
    private final AnalyticController analyticController;
    private final ImportDataCsv importData;
    private final ExportDataService exportData;
    @Autowired
    TerminalController(DomainFacadeController displayController, ImportDataCsv importData, ExportDataService exportData, AnalyticController analyticController) {
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
        System.out.println("6 - Сгруппировать доходы и расходы по категориям");
        System.out.println("7 - Подсчет разницы доходов за выбранный период");
        System.out.println("0 - Выход");
        System.out.print("Введите номер действия: ");
    }
    public void start() {

        try {
            //importData.importData("datajson\\bankAccounts.json", "datajson\\categories.json", "datajson\\operations.json");
            importData.importData("datacsv\\bankAccounts.csv", "datacsv\\categories.csv", "datacsv\\operations.csv");
        }
        catch (Exception e) {
            System.out.println("Ошибка импорта данных: " + e.getMessage());
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
                        System.out.println("Ошибка добавления счета: " + e.getMessage());
                    } catch (InvalidArgumentException e) {
                        System.out.println("Ошибка добавления счета: " + e.getMessage());
                    }

                    break;
                case 2:
                    try {
                        addCategory();
                    } catch (InputMismatchException e) {
                        scanner.nextLine();
                        System.out.println("Ошибка добавления категории: " + e.getMessage());
                    } catch (InvalidArgumentException e) {
                        System.out.println("Ошибка добавления категории: " + e.getMessage());
                    }

                    break;
                case 3:
                    try {
                        addOperation();
                    } catch (InputMismatchException e) {
                        scanner.nextLine();
                        System.out.println("Ошибка добавления операции: " + e.getMessage());
                    } catch (ParseException | InvalidArgumentException e) {
                        System.out.println("Ошибка добавления операции: " + e.getMessage());
                    }

                    break;
                case 4:
                    displayData();
                    break;
                case 5:
                    analyticController.RecountBalance();
                    break;
                case 6:
                    analyticController.GroupByCategory();
                    break;
                case 7:
                    countDifference();

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
            System.out.println("Ошибка экспорта данных: " + e.getMessage());
        }
    }

    private void addBankAccount() throws InvalidArgumentException {
        BankAccountBuilder builder = new BankAccountBuilder();

        System.out.print("Введите название счета: ");
        builder = builder.setName(scanner.nextLine());

        System.out.print("Введите баланс счета: ");
        builder = builder.setBalance(scanner.nextDouble());
        scanner.nextLine();

        domainFacadeController.getBankFacade().getBankAccountService().add(builder.build());
        System.out.println("Счет добавлен!");
    }

    private void addCategory() throws InvalidArgumentException {
        CategoryBuilder builder = new CategoryBuilder();

        System.out.print("Введите тип категории (доход/расход): ");
        builder = builder.setType(scanner.nextLine().toLowerCase());

        System.out.print("Введите название категории: ");
        builder = builder.setName(scanner.nextLine());

        domainFacadeController.getBankFacade().getCategoryService().add(builder.build());
        System.out.println("Категория добавлена!");
    }

    private void addOperation() throws ParseException, InvalidArgumentException {
        OperationBuilder builder = new OperationBuilder();

        System.out.print("Введите имя банковского счета: ");
        builder = builder.setBankAccountId(domainFacadeController.getBankFacade().
                getBankAccountService().getIdByName(scanner.nextLine()));

        System.out.print("Введите сумму: ");
        builder = builder.setAmount(scanner.nextDouble());
        scanner.nextLine();

        System.out.print("Введите дату (YYYY-MM-DD): ");
        builder = builder.setDate(scanner.nextLine());

        System.out.print("Введите описание: ");
        builder = builder.setDescription(scanner.nextLine());

        System.out.print("Введите имя категории: ");
        String name = scanner.nextLine();
        String id = domainFacadeController.getBankFacade().
                getCategoryService().getIdByName(name);

        builder = builder.setCategoryId(id);

        Category category = domainFacadeController.getBankFacade().getCategoryService().getById(id);

        builder.setType(category.getType().toString());

        domainFacadeController.getBankFacade().getOperationService().add(builder.build());
        System.out.println("Операция добавлена!");
    }

    private void displayData() {
        domainFacadeController.displayAll();
    }

    private void countDifference() {
        Date startDate;
        Date endDate;
        System.out.println("Введите начальную дату в формате YYYY-MM-DD");
        String startDateStr = scanner.nextLine();
        try {
            startDate = Constants.DateFormat.parse(startDateStr);
        } catch (Exception e) {
            System.out.println("Некорректная дата");
            return;
        }
        System.out.println("Введите конечную дату в формате YYYY-MM-DD");
        String endDateStr = scanner.nextLine();
        try {
            endDate = Constants.DateFormat.parse(endDateStr);
        } catch (Exception e) {
            System.out.println("Некорректная дата");
            return;
        }

        try {
            analyticController.CountDifference(startDate, endDate);
        } catch (Exception e) {
            System.out.println("Ошибка подсчета разницы доходов за выбранный период: " + e.getMessage());
        }
    }
}

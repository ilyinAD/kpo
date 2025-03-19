package org.example.controllers;

import org.example.builders.BankAccountBuilder;
import org.example.builders.CategoryBuilder;
import org.example.builders.OperationBuilder;
import org.example.constants.Constants;
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
    private final DomainFacadeController domainFacadeController;
    private final AnalyticController analyticController;
    private final ImportDataCsv importData;
    private final ExportDataService exportData;
    @Autowired
    TerminalController(DomainFacadeController displayController, ImportDataCsv importData, ExportDataService exportData, AnalyticController analyticController) {
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

    private String isValidInput(Scanner scanner) {
        String fileFormat = "";
        String input = scanner.nextLine();
        switch (input) {
            case "json":
                fileFormat = "json";
                break;
            case "csv":
                fileFormat = "csv";
                break;
            case "yaml":
                fileFormat = "yaml";
                break;
            default:
                System.out.println("Некорректный формат файла. Выберите json или csv или yaml.");
                break;
        }
        return fileFormat;
    }

    private String getFolderPath(String fileFormat) {
        switch (fileFormat) {
            case "json":
                return Constants.PathToJsonFolder;
            case "csv":
                return Constants.PathToCsvFolder;
            case "yaml":
                return Constants.PathToYamlFolder;
        }

        return "";
    }
    public void start(Scanner scanner) {
        boolean validInput = false;
        String importFileFormat = "";
        while (!validInput) {
            validInput = true;
            System.out.println("Введите формат файла из которого импортировать данные");
            importFileFormat = isValidInput(scanner);
            if (importFileFormat.isEmpty()) {
                validInput = false;
            }
        }

        validInput = false;
        String exportFileFormat = "";
        while (!validInput) {
            validInput = true;
            System.out.println("Введите формат файла в которыый экспортировать данные");
            exportFileFormat = isValidInput(scanner);
            if (exportFileFormat.isEmpty()) {
                validInput = false;
            }
        }

        String importFolderPath = getFolderPath(importFileFormat);
        String exportFolderPath = getFolderPath(exportFileFormat);

        try {
            //importData.importData("datajson\\bankAccounts.json", "datajson\\categories.json", "datajson\\operations.json");
            importData.importData(importFolderPath + "\\bankAccounts." + importFileFormat, importFolderPath + "\\categories." + importFileFormat, importFolderPath + "\\operations." + importFileFormat);
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
                        addBankAccount(scanner);
                    } catch (InputMismatchException e) {
                        scanner.nextLine();
                        System.out.println("Ошибка добавления счета: " + e.getMessage());
                    } catch (InvalidArgumentException e) {
                        System.out.println("Ошибка добавления счета: " + e.getMessage());
                    }

                    break;
                case 2:
                    try {
                        addCategory(scanner);
                    } catch (InputMismatchException e) {
                        scanner.nextLine();
                        System.out.println("Ошибка добавления категории: " + e.getMessage());
                    } catch (InvalidArgumentException e) {
                        System.out.println("Ошибка добавления категории: " + e.getMessage());
                    }

                    break;
                case 3:
                    try {
                        addOperation(scanner);
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
                    try {
                        analyticController.RecountBalance();
                    } catch (InvalidArgumentException e) {
                        System.out.println("Ошибка пересчета баланса: " + e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        analyticController.GroupByCategory();
                    } catch (InvalidArgumentException e) {
                        System.out.println("Ошибка группировки по категориям: " + e.getMessage());
                    }

                    break;
                case 7:
                    countDifference(scanner);

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
            exportData.Export(exportFolderPath + "\\bankAccounts." + exportFileFormat, exportFolderPath + "\\categories." + exportFileFormat, exportFolderPath + "\\operations." + exportFileFormat, exportFileFormat);
        } catch (Exception e) {
            System.out.println("Ошибка экспорта данных: " + e.getMessage());
        }
    }

    private void addBankAccount(Scanner scanner) throws InvalidArgumentException {
        BankAccountBuilder builder = new BankAccountBuilder();

        System.out.print("Введите название счета: ");
        builder = builder.setName(scanner.nextLine());

        System.out.print("Введите баланс счета: ");
        builder = builder.setBalance(scanner.nextDouble());
        scanner.nextLine();

        domainFacadeController.getBankFacade().getBankAccountService().add(builder.build());
        System.out.println("Счет добавлен!");
    }

    private void addCategory(Scanner scanner) throws InvalidArgumentException {
        CategoryBuilder builder = new CategoryBuilder();

        System.out.print("Введите тип категории (доход/расход): ");
        builder = builder.setType(scanner.nextLine().toLowerCase());

        System.out.print("Введите название категории: ");
        builder = builder.setName(scanner.nextLine());

        domainFacadeController.getBankFacade().getCategoryService().add(builder.build());
        System.out.println("Категория добавлена!");
    }

    private void addOperation(Scanner scanner) throws ParseException, InvalidArgumentException {
        OperationBuilder builder = new OperationBuilder(domainFacadeController.getBankFacade());

        System.out.print("Введите имя банковского счета: ");
        String accountName = scanner.nextLine();
        builder = builder.setBankAccountName(accountName);
//        builder = builder.setBankAccountId(domainFacadeController.getBankFacade().
//                getBankAccountService().getByName(scanner.nextLine()).getId());

        System.out.print("Введите сумму: ");
        builder = builder.setAmount(scanner.nextDouble());
        scanner.nextLine();

        System.out.print("Введите дату (YYYY-MM-DD): ");
        builder = builder.setDate(scanner.nextLine());

        System.out.print("Введите описание: ");
        builder = builder.setDescription(scanner.nextLine());

        System.out.print("Введите имя категории: ");
        String categoryName = scanner.nextLine();
        builder = builder.setCategoryName(categoryName);
//        String id = domainFacadeController.getBankFacade().
//                getCategoryService().getByName(name).getId();
//
//        builder = builder.setCategoryId(id);

//        Category category = domainFacadeController.getBankFacade().getCategoryService().findById(id);
//
//        builder.setType(category.getType().toString());

        domainFacadeController.getBankFacade().getOperationService().add(builder.build());
        System.out.println("Операция добавлена!");
    }

    private void displayData() {
        domainFacadeController.displayAll();
    }

    private void countDifference(Scanner scanner) {
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

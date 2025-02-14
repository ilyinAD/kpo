package org.example;

import org.example.domain.*;
import org.example.controllers.ZooController;
import org.example.services.AnimalFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.util.*;
@EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.class})
@ComponentScan(basePackages = "org.example")
public class Main implements CommandLineRunner{
    private final ZooController zooController;
    private final AnimalFactory animalFactory;
    @Autowired
    public Main(ZooController zooController, AnimalFactory animalFactory) {
        this.zooController = zooController;
        this.animalFactory = animalFactory;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Добавить травоядное животное");
            System.out.println("2. Добавить хищниое животное");
            System.out.println("3. Вывести общее потребление еды в кг, всеми животными");
            System.out.println("4. Вывести животных, которые могут попасть в контактный зоопарк");
            System.out.println("5. Выход");
            System.out.print("Выберите опцию: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            String name;
            int food, kind, num;
            AnimalType animalType;
            Animal animal;
            switch (choice) {
                case 1:
                    System.out.print("Введите животное которое хотите добавить." +
                            " Доступные животные: кролик, обезьяна\n");
                    name = scanner.nextLine();
                    try {
                        animalType = AnimalType.fromString(name);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Такого животного нет");
                        break;
                    }

                    if (animalType == null) {
                        break;
                    }

                    System.out.print("Введите количество еды (кг): ");
                    food = scanner.nextInt();
                    System.out.print("Введите уровень доброты (0-10): ");
                    kind = scanner.nextInt();
                    animal = animalFactory.createAnimal(animalType, food, kind);
                    zooController.addAnimal(animal);
                    break;
                case 2:
                    System.out.print("Введите животное которое хотите добавить." +
                            " Доступные животные: тигр, волк\n");
                    name = scanner.nextLine();

                    try {
                        animalType = AnimalType.fromString(name);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Такого животного нет");
                        break;
                    }

                    if (animalType == null) {
                        break;
                    }

                    System.out.print("Введите количество еды (кг): ");
                    food = scanner.nextInt();
                    animal = animalFactory.createAnimal(animalType, food, null);
                    zooController.addAnimal(animal);
                    break;
                case 3:
                    zooController.printGeneralFood();
                    break;
                case 4:
                    zooController.printContactAnimals();
                    break;
                case 5:
                    System.out.println("Выход...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Данная опция некорректна, использоуйте цифру от 1 до 5 без пробелов");
            }
        }
    }


    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

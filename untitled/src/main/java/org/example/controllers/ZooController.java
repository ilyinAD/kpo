package org.example.controllers;

import org.example.domain.Animal;
import org.example.services.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;
@Controller
public class ZooController {
    private final ZooService zooService;

    @Autowired
    public ZooController(ZooService zooService) {
        this.zooService = zooService;
    }

    public void addAnimal(Animal animal) {
        Optional<String> error = zooService.addAnimal(animal);
        if (error.isPresent()) {
            System.out.println("Животное " + animal.getName() + " с типом " + animal.getType() + " не прошло проверку здоровья.");
        } else {
            System.out.println("Животное " + animal.getName() + " с типом " + animal.getType() + "было добавлено.");
        }
    }

    public void printGeneralFood() {
        final int result = zooService.getGeneralFood();
        System.out.println("Общее потребление еды: " + result + " кг в день");
    }

    public void printContactAnimals() {
        System.out.println("Животные, которых можно отправить в контактный зоопарк:");
        zooService.getContactAnimals().forEach(a -> System.out.println(a.getName()));
    }
}

package org.example.controllers;

import org.example.Main;
import org.example.domain.Predator;
import org.example.domain.Tiger;
import org.example.services.VetClinic;
import org.example.services.ZooService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;


public class ZooControllerTest {
    ZooService zooService = new ZooService(new VetClinic());
    ZooController zooController = new ZooController(zooService);

    @Test
    void addAnimalTest() {
        zooController.addAnimal(new Tiger(10, 2));
    }

    @Test
    void printGeneralFoodTest() {
        zooController.addAnimal(new Tiger( 10, 2));
        zooController.printGeneralFood();
    }

    @Test
    void printContactAnimalsTest() {
        zooController.addAnimal(new Tiger( 10, 2));
        zooController.printContactAnimals();
    }
}

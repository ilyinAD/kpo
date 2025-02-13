package org.example.services;

import org.example.controllers.ZooController;
import org.example.domain.Animal;
import org.example.domain.Herbivore;
import org.example.domain.Predator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import java.util.ArrayList;

public class ZooServiceTest {


    @Test
    void addAnimalTestDontPassOnHealth() {
        Animal animal = new Herbivore("parrot", 100, 1, 5);

        VetClinic vetClinicMock = mock(VetClinic.class);
        when(vetClinicMock.checkHealth(animal)).thenReturn(false);
        ZooService zooService = new ZooService(vetClinicMock);
        Optional<String> result = zooService.addAnimal(animal);

        assertEquals("Ошибка добавления животного: проверка здоровья не пройдена.", result.orElse(""));
    }

    @Test
    void addAnimalTestPassOnHealth() {
        Animal animal = new Herbivore("parrot", 100, 1, 5);

        VetClinic vetClinicMock = mock(VetClinic.class);
        when(vetClinicMock.checkHealth(animal)).thenReturn(true);
        ZooService zooService = new ZooService(vetClinicMock);
        Optional<String> result = zooService.addAnimal(animal);

        assertFalse(result.isPresent());
    }

    @Test
    void getFoodConsumptionTest() {
        VetClinic vetClinicMock = mock(VetClinic.class);
        Animal parrot = new Herbivore("parrot", 10, 1, 5);
        Animal lion = new Predator("Lion", 20, 2);
        Animal rabbit = new Herbivore("Rabbit", 15, 3, 5);
        when(vetClinicMock.checkHealth(parrot)).thenReturn(true);
        when(vetClinicMock.checkHealth(lion)).thenReturn(false);
        when(vetClinicMock.checkHealth(rabbit)).thenReturn(true);
        ZooService zooService = new ZooService(vetClinicMock);
        zooService.addAnimal(parrot);
        zooService.addAnimal(lion);
        zooService.addAnimal(rabbit);
        assertEquals(25, zooService.getGeneralFood());
    }

    @Test
    void getContactZooAnimalsTest() {
        VetClinic vetClinicMock = mock(VetClinic.class);
        Animal parrot = new Herbivore("parrot", 10, 1, 5);
        Animal lion = new Predator("Lion", 20, 2);
        Animal rabbit = new Herbivore("Rabbit", 15, 3, 6);
        when(vetClinicMock.checkHealth(parrot)).thenReturn(true);
        when(vetClinicMock.checkHealth(lion)).thenReturn(false);
        when(vetClinicMock.checkHealth(rabbit)).thenReturn(true);
        ZooService zooService = new ZooService(vetClinicMock);
        zooService.addAnimal(parrot);
        zooService.addAnimal(lion);
        zooService.addAnimal(rabbit);

        List<Animal> rightAnswer = new ArrayList<>();
        rightAnswer.add(rabbit);
        assertEquals(rightAnswer, zooService.getContactAnimals());
    }

}

package org.example.services;

import org.example.domain.*;
import org.junit.jupiter.api.Test;

public class AnimalFactoryTest {
    @Test
    public void createAnimal_returnsRabbit() {
        AnimalFactory animalFactory = new AnimalFactory();
        Animal animal = animalFactory.createAnimal(AnimalType.RABBIT, 10, 10);
        assert animal instanceof Rabbit;
    }

    @Test
    public void createAnimal_returnsMonkey() {
        AnimalFactory animalFactory = new AnimalFactory();
        Animal animal = animalFactory.createAnimal(AnimalType.MONKEY, 10, 10);
        assert animal instanceof Monkey;
    }

    @Test
    public void createAnimal_returnsWolf() {
        AnimalFactory animalFactory = new AnimalFactory();
        Animal animal = animalFactory.createAnimal(AnimalType.WOLF, 10, null);
        assert animal instanceof Wolf;
    }

    @Test
    public void createAnimal_returnsTiger() {
        AnimalFactory animalFactory = new AnimalFactory();
        Animal animal = animalFactory.createAnimal(AnimalType.TIGER, 10, null);
        assert animal instanceof Tiger;
    }
}

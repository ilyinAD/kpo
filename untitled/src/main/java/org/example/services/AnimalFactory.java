package org.example.services;

import org.example.domain.*;
import org.springframework.stereotype.Component;

@Component
public class AnimalFactory {
    private int num;
    public AnimalFactory() {
        num = 0;
    }
    public Animal createAnimal(AnimalType animalType, int food, Integer kindness) {
        this.num += 1;
        return switch (animalType) {
            case RABBIT -> new Rabbit(food, num, kindness);
            case WOLF -> new Wolf(food, num);
            case TIGER -> new Tiger(food, num);
            case MONKEY -> new Monkey(food, num, kindness);
        };
    }
}

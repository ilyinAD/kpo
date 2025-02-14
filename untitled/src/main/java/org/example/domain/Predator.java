package org.example.domain;

import org.example.domain.Animal;

public abstract class Predator extends Animal {
    public Predator(int food, int number) {
        super(food, number);
        type = "Хищник";
    }
}

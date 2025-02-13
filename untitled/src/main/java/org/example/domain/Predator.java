package org.example.domain;

import org.example.domain.Animal;

public class Predator extends Animal {
    public Predator(String name, int food, int number) {
        super(name, food, number);
        type = "Хищник";
    }
}

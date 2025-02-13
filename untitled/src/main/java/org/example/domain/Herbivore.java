package org.example.domain;

public class Herbivore extends Animal {
    private int kind;

    public Herbivore(String name, int food, int number, int kindness) {
        super(name, food, number);
        this.kind = kindness;
        type = "Травоядный";
    }

    public int getKindness() {
        return kind;
    }
}

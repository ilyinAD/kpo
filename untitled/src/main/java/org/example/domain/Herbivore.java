package org.example.domain;

public abstract class Herbivore extends Animal {
    private int kind;

    public Herbivore(int food, int number, int kindness) {
        super(food, number);
        this.kind = kindness;
        type = "Травоядный";
    }

    public int getKindness() {
        return kind;
    }
}

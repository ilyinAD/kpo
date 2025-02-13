package org.example.domain;

import org.example.interfaces.IAlive;
import org.example.interfaces.IInventory;

public abstract class Animal implements IAlive, IInventory {
    protected String name;
    protected int food;
    protected int number;
    protected String type;

    public Animal(String name, int food, int number) {
        this.name = name;
        this.food = food;
        this.number = number;
    }

    @Override
    public int getFood() {
        return food;
    }

    @Override
    public int getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}

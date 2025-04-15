package org.example.domain;

import lombok.Getter;

public enum AnimalType {
    Predator("predator"),
    Herbivore("herbivore"),
    Bird("bird"),
    Sea("sea");

    @Getter
    private String title;
    AnimalType(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return title;
    }

    public static AnimalType fromString(String text) {
        for (AnimalType animalType : AnimalType.values()) {
            if (animalType.title.equalsIgnoreCase(text)) {
                return animalType;
            }
        }

        return null;
    }
}

package org.example.domain;

public enum AnimalType {
    RABBIT, WOLF, TIGER, MONKEY;

    public static AnimalType fromString(String name) throws IllegalArgumentException {
        return switch (name.toLowerCase()) {
            case "кролик" -> RABBIT;
            case "волк" -> WOLF;
            case "тигр" -> TIGER;
            case "обезьяна" -> MONKEY;
            default -> throw new IllegalArgumentException("Неизвестное животное: " + name);
        };
    }
}

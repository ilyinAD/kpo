package org.example.domain;

import lombok.Getter;
import org.example.exceptions.EnclosureFullException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@Getter
public class Enclosure {
    private AnimalType animalType;
    private int maxAnimalsAmount;
    private int area;
    private List<Animal> animals = new ArrayList<>();
    private String id;
    public int getAnimalsAmount() {
        return animals.size();
    }
    Enclosure(AnimalType animalType, int maxAnimalsAmount, int area) {
        this.animalType = animalType;
        this.maxAnimalsAmount = maxAnimalsAmount;
        this.area = area;
        this.id = IdGenerator.generateID();
    }

    Enclosure(AnimalType animalType, int maxAnimalsAmount, int area, Supplier<String> generateID) {
        this.animalType = animalType;
        this.maxAnimalsAmount = maxAnimalsAmount;
        this.area = area;
        this.id = generateID.get();
    }

    void save(Animal animal) throws EnclosureFullException {
        if (animals.size() == maxAnimalsAmount) {
            throw new EnclosureFullException("Enclosure is full");
        }

        boolean exists = animals.stream()
                .anyMatch(el -> Objects.equals(el.getId(), animal.getId()));
        if (!exists)
            animals.add(animal);
    }

    void delete(Animal animal) {
        animals.remove(animal);
    }
}

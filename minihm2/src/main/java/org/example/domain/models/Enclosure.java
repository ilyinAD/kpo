package org.example.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.example.exceptions.EnclosureException;

import java.util.function.Supplier;

@Getter
public class Enclosure {
    private AnimalType animalType;
    private int maxAnimalsAmount;
    private int area;
    private int currentAnimalAmount;
    private String id;
    @JsonCreator
    Enclosure(@JsonProperty("animalType") AnimalType animalType,
              @JsonProperty("maxAnimalsAmount") int maxAnimalsAmount,
              @JsonProperty("area") int area) {
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

    public void save() throws EnclosureException {
        if (currentAnimalAmount == maxAnimalsAmount) {
            throw new EnclosureException("Enclosure is full");
        }

        currentAnimalAmount++;
    }

    public void delete() throws EnclosureException {
        if (currentAnimalAmount == 0) {
            throw new EnclosureException("Enclosure is empty");
        }

        currentAnimalAmount--;
    }
}

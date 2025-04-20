package org.example.domain.models;

import lombok.Getter;
import org.example.exceptions.EnclosureFullException;

import java.time.LocalDate;
import java.util.function.Supplier;

@Getter
public class Animal {
    private String species;
    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private HealthStatus healthStatus;
    private String favoriteFood;
    private Enclosure enclosure;
    private String id;
    public Animal(String species, String name, LocalDate birthDate, Gender gender, String favoriteFood, Supplier<String> generateID) {
        this.species = species;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.favoriteFood = favoriteFood;
        this.healthStatus = HealthStatus.HEALTHY;
        this.id = generateID.get();
    }
    public Animal(String species, String name, LocalDate birthDate, Gender gender, String favoriteFood) {
        this.species = species;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.favoriteFood = favoriteFood;
        this.healthStatus = HealthStatus.HEALTHY;
        this.id = IdGenerator.generateID();
    }
    public enum Gender { MALE, FEMALE }
    public enum HealthStatus { HEALTHY, ILL }

    public void feed() {
        System.out.println("animal: " + species + " with name" + name + "was fed");
    }
    public void cure() {
        healthStatus = HealthStatus.HEALTHY;
    }
    public void moveToEnclosure(Enclosure newEnclosure) throws EnclosureFullException {
        if (this.enclosure != null) {
            this.enclosure.delete(this);
        }

        newEnclosure.save(this);
        this.enclosure = newEnclosure;
    }
}

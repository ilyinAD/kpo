package org.example.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.example.exceptions.EnclosureException;

import java.time.LocalDate;
import java.util.function.Supplier;

@Getter
@Setter
public class Animal {
    private String species;
    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private HealthStatus healthStatus;
    private String favoriteFood;
    private String enclosureID;
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
    @JsonCreator
    public Animal( @JsonProperty("species") String species,
                   @JsonProperty("name") String name,
                   @JsonProperty("birthDate") LocalDate birthDate,
                   @JsonProperty("gender") Gender gender,
                   @JsonProperty("favoriteFood") String favoriteFood) {
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
    public void moveToEnclosure(Enclosure newEnclosure) throws EnclosureException {
        this.enclosureID = newEnclosure.getId();
    }
}

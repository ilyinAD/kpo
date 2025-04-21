package org.example.application.interfaces.domain;

import org.example.domain.models.Animal;

import java.util.List;
import java.util.Optional;

public interface IAnimalService {
    Animal save(Animal animal);

    Animal delete(Animal animal);

    Optional<Animal> getByID(String animalID);

    List<Animal> getAnimals();

    Animal deleteByID(String id);
}

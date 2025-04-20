package org.example.domain.repositoryinterfaces;

import org.example.domain.models.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalRepositoryInterface {
    Animal add(Animal animal);
    Animal update(Animal animal);
    Animal delete(Animal animal);
    Optional<Animal> getByID(String id);
    List<Animal> getAnimals();
}

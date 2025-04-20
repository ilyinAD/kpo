package org.example.domain.repositoryinterfaces;

import org.example.domain.models.Animal;

import java.util.Optional;

public interface AnimalRepositoryInterface {
    void add(Animal animal);
    void update(Animal animal);
    void delete(Animal animal);
    Optional<Animal> getByID(String id);
}

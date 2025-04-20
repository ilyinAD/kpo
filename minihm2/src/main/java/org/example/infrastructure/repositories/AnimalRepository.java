package org.example.infrastructure.repositories;

import lombok.Getter;
import org.example.domain.models.Animal;
import org.example.domain.repositoryinterfaces.AnimalRepositoryInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@Getter
public class AnimalRepository implements AnimalRepositoryInterface {
    private List<Animal> animals = new ArrayList<>();


    public Animal add(Animal animal) {
        animals.add(animal);
        return animal;
    }

    public Animal update(Animal animal) {
        for (int i = 0; i < animals.size(); ++i) {
            if (Objects.equals(animals.get(i).getId(), animal.getId())) {
                animals.set(i, animal);
                return animals.get(i);
            }
        }
        throw new IllegalStateException("no animal in list");
    }
    public Animal delete(Animal animal) {
        animals.remove(animal);
        return animal;
    }
    public Optional<Animal> getByID(String id) {
        return animals.stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .findFirst();
    }
}

package org.example.repositories;

import org.example.domain.Animal;
import org.example.domain.IdGenerator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class AnimalRepository {
    private List<Animal> animals = new ArrayList<>();
    public void save(Animal animal) {
        boolean exists = animals.stream()
                .anyMatch(el -> Objects.equals(el.getId(), animal.getId()));
        if (!exists) {
            add(animal);
        } else {
            update(animal);
        }
    }

    private void add(Animal animal) {
        animals.add(animal);
    }

    private void update(Animal animal) {
        for (int i = 0; i < animals.size(); ++i) {
            if (Objects.equals(animals.get(i).getId(), animal.getId())) {
                animals.set(i, animal);
                return;
            }
        }
    }
    public void delete(Animal animal) {
        animals.remove(animal);
    }
    public Optional<Animal> getByID(String id) {
        return animals.stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .findFirst();
    }
}

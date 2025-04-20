package org.example.application.services.domain;

import org.example.domain.models.Animal;
import org.example.domain.repositoryinterfaces.AnimalRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    final AnimalRepositoryInterface animalRepository;
    @Autowired
    AnimalService(AnimalRepositoryInterface animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Animal save(Animal animal) {
        boolean exists = animalRepository.getByID(animal.getId()).isPresent();
        if (!exists) {
           return animalRepository.add(animal);
        } else {
            return animalRepository.update(animal);
        }
    }

    public Animal delete(Animal animal) {
        return animalRepository.delete(animal);
    }
    public Optional<Animal> getByID(String animalID) {
        return animalRepository.getByID(animalID);
    }

    public List<Animal> getAnimals() {
        return animalRepository.getAnimals();
    }
    public Animal deleteByID(String id) {
        Animal animal = animalRepository.getByID(id)
                .orElseThrow(() -> new IllegalArgumentException("Animal with id " + id + " wasn't found"));
        return animalRepository.delete(animal);
    }
}

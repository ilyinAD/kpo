package org.example.application.services;

import org.example.domain.models.Animal;
import org.example.domain.repositoryinterfaces.AnimalRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnimalService {
    final AnimalRepositoryInterface animalRepository;
    @Autowired
    AnimalService(AnimalRepositoryInterface animalRepository) {
        this.animalRepository = animalRepository;
    }

    public void save(Animal animal) {
        boolean exists = animalRepository.getByID(animal.getId()).isPresent();
        if (!exists) {
           animalRepository.add(animal);
        } else {
            animalRepository.update(animal);
        }
    }

    public Optional<Animal> getByID(String animalID) {
        return animalRepository.getByID(animalID);
    }


}

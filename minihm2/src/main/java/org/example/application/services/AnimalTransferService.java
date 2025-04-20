package org.example.application.services;

import org.example.domain.models.Animal;
import org.example.domain.models.Enclosure;
import org.example.domain.repositoryinterfaces.AnimalRepositoryInterface;
import org.example.domain.repositoryinterfaces.EnclosureRepositoryInterface;
import org.example.infrastructure.repositories.AnimalRepository;
import org.example.infrastructure.repositories.EnclosureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalTransferService {
    private final AnimalService animalRepository;
    private final EnclosureService enclosureRepository;
    @Autowired
    AnimalTransferService(AnimalService animalRepository, EnclosureService enclosureService) {
        this.animalRepository = animalRepository;
        this.enclosureRepository = enclosureService;
    }

    public void transferAnimal(String animalID, String enclosureID) throws Exception {
        Animal animal = animalRepository.getByID(animalID)
                .orElseThrow(() -> new IllegalArgumentException("Animal with id " + animalID + " wasn't found"));

        Enclosure enclosure = enclosureRepository.getByID(enclosureID)
                .orElseThrow(() -> new IllegalArgumentException("Enclosure with id " + enclosureID + "wasn't found"));
        animal.moveToEnclosure(enclosure);

        animalRepository.save(animal);
        enclosureRepository.save(enclosure);
    }
}

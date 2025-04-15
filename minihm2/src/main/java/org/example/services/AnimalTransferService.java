package org.example.services;

import org.example.domain.Animal;
import org.example.domain.Enclosure;
import org.example.exceptions.EnclosureFullException;
import org.example.repositories.AnimalRepository;
import org.example.repositories.EnclosureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalTransferService {
    private final AnimalRepository animalRepository;
    private final EnclosureRepository enclosureRepository;
    @Autowired
    AnimalTransferService(AnimalRepository animalRepository, EnclosureRepository enclosureRepository) {
        this.animalRepository = animalRepository;
        this.enclosureRepository = enclosureRepository;
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

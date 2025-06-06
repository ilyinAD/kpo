package org.example.application.services;

import org.example.application.events.AnimalMovedEvent;
import org.example.application.interfaces.IAnimalTransferService;
import org.example.application.services.domain.AnimalService;
import org.example.application.services.domain.EnclosureService;
import org.example.domain.models.Animal;
import org.example.domain.models.Enclosure;
import org.example.exceptions.EnclosureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;


@Service
public class AnimalTransferService implements IAnimalTransferService {
    private final AnimalService animalService;
    private final EnclosureService enclosureService;
    private final ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    AnimalTransferService(AnimalService animalRepository, EnclosureService enclosureService, ApplicationEventPublisher applicationEventPublisher) {
        this.animalService = animalRepository;
        this.enclosureService = enclosureService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void transferAnimal(String animalID, String enclosureID) throws Exception {
        Animal animal = animalService.getByID(animalID)
                .orElseThrow(() -> new IllegalArgumentException("Animal with id " + animalID + " wasn't found"));

        Enclosure enclosure = enclosureService.getByID(enclosureID)
                .orElseThrow(() -> new IllegalArgumentException("Enclosure with id " + enclosureID + "wasn't found"));
        Optional<Enclosure> oldEnclosure = enclosureService.getByID(animal.getEnclosureID());


        enclosure.save();
        oldEnclosure.ifPresent(value -> {
            try {
                value.delete();
            } catch (EnclosureException e) {
                throw new RuntimeException(e);
            }
        });
        animal.moveToEnclosure(enclosure);


        animalService.save(animal);
        enclosureService.save(enclosure);
        oldEnclosure.ifPresent(enclosureService::save);


        applicationEventPublisher.publishEvent(new AnimalMovedEvent(animalID, enclosureID));
    }
}

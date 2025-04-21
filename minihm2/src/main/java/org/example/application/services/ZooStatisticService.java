package org.example.application.services;

import org.example.application.interfaces.IZooStatisticService;
import org.example.application.services.domain.AnimalService;
import org.example.application.services.domain.EnclosureService;
import org.example.domain.models.Enclosure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZooStatisticService implements IZooStatisticService {
    private final AnimalService animalService;
    private final EnclosureService enclosureService;
    @Autowired
    public ZooStatisticService(AnimalService animalRepository, EnclosureService enclosureRepository) {
        this.animalService = animalRepository;
        this.enclosureService = enclosureRepository;
    }

    @Override
    public long countAnimals() {
        return animalService.getAnimals().size();
    }

    @Override
    public long countFreeEnclosures() {
        return enclosureService.getEnclosures().stream()
                .filter(e -> e.getCurrentAnimalAmount() < e.getMaxAnimalsAmount())
                .count();
    }

    @Override
    public List<Enclosure> getFulledEnclosures() {
        return enclosureService.getEnclosures().stream()
                .filter(e -> e.getCurrentAnimalAmount() == e.getMaxAnimalsAmount())
                .collect(Collectors.toList());
    }

    @Override
    public List<Enclosure> getEmptyEnclosures() {
        return enclosureService.getEnclosures().stream()
                .filter(e -> e.getCurrentAnimalAmount() < e.getMaxAnimalsAmount())
                .collect(Collectors.toList());
    }
}

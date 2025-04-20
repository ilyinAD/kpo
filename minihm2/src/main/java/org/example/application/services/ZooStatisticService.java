package org.example.application.services;

import org.example.application.services.domain.AnimalService;
import org.example.application.services.domain.EnclosureService;
import org.example.domain.models.Enclosure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZooStatisticService {
    private final AnimalService animalService;
    private final EnclosureService enclosureService;
    @Autowired
    public ZooStatisticService(AnimalService animalRepository, EnclosureService enclosureRepository) {
        this.animalService = animalRepository;
        this.enclosureService = enclosureRepository;
    }

    public long countAnimals() {
        return animalService.getAnimals().size();
    }

    public long countFreeEnclosures() {
        return enclosureService.getEnclosures().stream()
                .filter(e -> e.getAnimalsAmount() < e.getMaxAnimalsAmount())
                .count();
    }

    public List<Enclosure> getFulledEnclosures() {
        return enclosureService.getEnclosures().stream()
                .filter(e -> e.getAnimalsAmount() == e.getMaxAnimalsAmount())
                .collect(Collectors.toList());
    }

    public List<Enclosure> getEmptyEnclosures() {
        return enclosureService.getEnclosures().stream()
                .filter(e -> e.getAnimalsAmount() < e.getAnimalsAmount())
                .collect(Collectors.toList());
    }
}

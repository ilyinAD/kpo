package org.example.services;

import org.example.domain.Animal;
import org.example.domain.Enclosure;
import org.example.repositories.AnimalRepository;
import org.example.repositories.EnclosureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZooStatisticService {
    private final AnimalRepository animalRepository;
    private final EnclosureRepository enclosureRepository;
    @Autowired
    public ZooStatisticService(AnimalRepository animalRepository, EnclosureRepository enclosureRepository) {
        this.animalRepository = animalRepository;
        this.enclosureRepository = enclosureRepository;
    }

    public long countAnimals(List<Animal> animals) {
        return animals.size();
    }

    public long countFreeEnclosures(List<Enclosure> enclosures) {
        return enclosures.stream()
                .filter(e -> e.getAnimalsAmount() < e.getMaxAnimalsAmount())
                .count();
    }

    public List<Enclosure> getFulledEnclosures(List<Enclosure> enclosures) {
        return enclosures.stream()
                .filter(e -> e.getAnimalsAmount() == e.getMaxAnimalsAmount())
                .collect(Collectors.toList());
    }
}

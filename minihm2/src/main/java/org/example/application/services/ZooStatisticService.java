package org.example.application.services;

import org.example.domain.models.Animal;
import org.example.domain.models.Enclosure;
import org.example.domain.repositoryinterfaces.AnimalRepositoryInterface;
import org.example.domain.repositoryinterfaces.EnclosureRepositoryInterface;
import org.example.infrastructure.repositories.AnimalRepository;
import org.example.infrastructure.repositories.EnclosureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZooStatisticService {
    private final AnimalRepositoryInterface animalRepository;
    private final EnclosureRepositoryInterface enclosureRepository;
    @Autowired
    public ZooStatisticService(AnimalRepositoryInterface animalRepository, EnclosureRepositoryInterface enclosureRepository) {
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

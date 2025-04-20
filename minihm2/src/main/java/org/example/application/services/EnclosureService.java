package org.example.application.services;

import org.example.domain.models.Animal;
import org.example.domain.models.Enclosure;
import org.example.domain.repositoryinterfaces.EnclosureRepositoryInterface;
import org.example.infrastructure.repositories.EnclosureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
public class EnclosureService {
    final EnclosureRepositoryInterface enclosureRepository;
    @Autowired
    EnclosureService(EnclosureRepositoryInterface enclosureRepository) {
        this.enclosureRepository = enclosureRepository;
    }
    public void save(Enclosure enclosure) {
        boolean exists = enclosureRepository.getByID(enclosure.getId()).isPresent();
        if (!exists) {
            enclosureRepository.add(enclosure);
        } else {
            enclosureRepository.update(enclosure);
        }
    }
}

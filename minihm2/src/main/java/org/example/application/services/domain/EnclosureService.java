package org.example.application.services.domain;

import org.example.domain.models.Animal;
import org.example.domain.models.Enclosure;
import org.example.domain.repositoryinterfaces.EnclosureRepositoryInterface;
import org.example.infrastructure.repositories.EnclosureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EnclosureService {
    final EnclosureRepositoryInterface enclosureRepository;
    @Autowired
    EnclosureService(EnclosureRepositoryInterface enclosureRepository) {
        this.enclosureRepository = enclosureRepository;
    }
    public Enclosure save(Enclosure enclosure) {
        boolean exists = enclosureRepository.getByID(enclosure.getId()).isPresent();
        if (!exists) {
            return enclosureRepository.add(enclosure);
        } else {
            return enclosureRepository.update(enclosure);
        }
    }
    public Enclosure delete(Enclosure enclosure) {
        return enclosureRepository.delete(enclosure);
    }

    public Enclosure deleteByID(String id) {
        Enclosure enclosure = enclosureRepository.getByID(id)
                .orElseThrow(() -> new IllegalArgumentException("Animal with id " + id + " wasn't found"));
        return enclosureRepository.delete(enclosure);
    }
    public Optional<Enclosure> getByID(String enclosureID) {
        return enclosureRepository.getByID(enclosureID);
    }

    public List<Enclosure> getEnclosures() {
        return enclosureRepository.getEnclosures();
    }
}

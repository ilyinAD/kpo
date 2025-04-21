package org.example.application.services.domain;

import org.example.application.interfaces.domain.IEnclosureService;
import org.example.domain.models.Enclosure;
import org.example.domain.repositoryinterfaces.EnclosureRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnclosureService implements IEnclosureService {
    final EnclosureRepositoryInterface enclosureRepository;
    @Autowired
    EnclosureService(EnclosureRepositoryInterface enclosureRepository) {
        this.enclosureRepository = enclosureRepository;
    }
    @Override
    public Enclosure save(Enclosure enclosure) {
        boolean exists = enclosureRepository.getByID(enclosure.getId()).isPresent();
        if (!exists) {
            return enclosureRepository.add(enclosure);
        } else {
            return enclosureRepository.update(enclosure);
        }
    }
    @Override
    public Enclosure delete(Enclosure enclosure) {
        return enclosureRepository.delete(enclosure);
    }

    @Override
    public Enclosure deleteByID(String id) {
        Enclosure enclosure = enclosureRepository.getByID(id)
                .orElseThrow(() -> new IllegalArgumentException("Animal with id " + id + " wasn't found"));
        return enclosureRepository.delete(enclosure);
    }
    @Override
    public Optional<Enclosure> getByID(String enclosureID) {
        return enclosureRepository.getByID(enclosureID);
    }

    @Override
    public List<Enclosure> getEnclosures() {
        return enclosureRepository.getEnclosures();
    }
}

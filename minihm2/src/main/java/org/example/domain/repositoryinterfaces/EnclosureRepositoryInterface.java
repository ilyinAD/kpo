package org.example.domain.repositoryinterfaces;

import org.example.domain.models.Enclosure;

import java.util.Optional;

public interface EnclosureRepositoryInterface {
    void add(Enclosure enclosure);
    void update(Enclosure enclosure);
    void delete(Enclosure enclosure);
    Optional<Enclosure> getByID(String id);
}

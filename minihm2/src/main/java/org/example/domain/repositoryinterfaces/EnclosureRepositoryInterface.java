package org.example.domain.repositoryinterfaces;

import org.example.domain.models.Enclosure;

import java.util.List;
import java.util.Optional;

public interface EnclosureRepositoryInterface {
    Enclosure add(Enclosure enclosure);
    Enclosure update(Enclosure enclosure);
    Enclosure delete(Enclosure enclosure);
    Optional<Enclosure> getByID(String id);
    List<Enclosure> getEnclosures();
}

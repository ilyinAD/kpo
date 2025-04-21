package org.example.application.interfaces.domain;

import org.example.domain.models.Enclosure;

import java.util.List;
import java.util.Optional;

public interface IEnclosureService {
    Enclosure save(Enclosure enclosure);

    Enclosure delete(Enclosure enclosure);

    Enclosure deleteByID(String id);

    Optional<Enclosure> getByID(String enclosureID);

    List<Enclosure> getEnclosures();
}

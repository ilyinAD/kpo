package org.example.application.interfaces;

import org.example.domain.models.Enclosure;

import java.util.List;

public interface IZooStatisticService {
    long countAnimals();

    long countFreeEnclosures();

    List<Enclosure> getFulledEnclosures();

    List<Enclosure> getEmptyEnclosures();
}

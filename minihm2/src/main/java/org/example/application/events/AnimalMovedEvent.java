package org.example.application.events;

import lombok.Getter;

@Getter
public class AnimalMovedEvent {
    final String animalID;
    final String enclosureID;
    public AnimalMovedEvent(String animalID, String enclosureID) {
        this.animalID = animalID;
        this.enclosureID = enclosureID;
    }
}

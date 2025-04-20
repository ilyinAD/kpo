package org.example.application.events;

import lombok.Getter;

@Getter
public class FeedingTimeEvent {
    final String animalID;
    final String enclosureID;
    public FeedingTimeEvent(String animalID, String enclosureID) {
        this.animalID = animalID;
        this.enclosureID = enclosureID;
    }
}

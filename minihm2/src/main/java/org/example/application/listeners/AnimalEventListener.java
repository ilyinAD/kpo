package org.example.application.listeners;

import org.example.application.events.AnimalMovedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AnimalEventListener {
    @EventListener
    public void handleAnimalMovedEvent(AnimalMovedEvent animalMovedEvent) {
        System.out.println("animal with id" + animalMovedEvent.getAnimalID() + "was moved to enclosure with id" + animalMovedEvent.getEnclosureID());
    }
}

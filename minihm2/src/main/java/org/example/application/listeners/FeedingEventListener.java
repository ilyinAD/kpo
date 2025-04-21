package org.example.application.listeners;

import org.example.application.events.FeedingTimeEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class FeedingEventListener {
    @EventListener
    public void handleFeedingEvent(FeedingTimeEvent feedingTimeEvent) {
        System.out.println("Scheduled feeding event with id " + feedingTimeEvent.getFeedingScheduleID() + " was executed");
    }
}

package org.example.application.events;

import lombok.Getter;

@Getter
public class FeedingTimeEvent {
    final String feedingScheduleID;
    public FeedingTimeEvent(String feedingScheduleID) {
        this.feedingScheduleID = feedingScheduleID;
    }
}

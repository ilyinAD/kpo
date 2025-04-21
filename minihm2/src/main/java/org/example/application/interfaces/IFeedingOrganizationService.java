package org.example.application.interfaces;

import org.example.domain.models.FeedingSchedule;

public interface IFeedingOrganizationService {
    void executeFeedingSchedule(String feedingScheduleID);

    void addFeedingSchedule(FeedingSchedule schedule);

    void markFeedingDone(FeedingSchedule schedule);
}

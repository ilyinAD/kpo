package org.example.application.interfaces.domain;

import org.example.domain.models.FeedingSchedule;

import java.util.List;
import java.util.Optional;

public interface IFeedingScheduleService {
    FeedingSchedule save(FeedingSchedule schedule);

    FeedingSchedule delete(FeedingSchedule schedule);

    Optional<FeedingSchedule> getByID(String scheduleID);

    List<FeedingSchedule> getSchedules();
}

package org.example.domain.repositoryinterfaces;

import org.example.domain.models.FeedingSchedule;

import java.util.List;
import java.util.Optional;

public interface FeedingScheduleRepositoryInterface {
    FeedingSchedule add(FeedingSchedule schedule);
    FeedingSchedule update(FeedingSchedule schedule);
    FeedingSchedule delete(FeedingSchedule schedule);
    Optional<FeedingSchedule> getByID(String scheduleID);
    List<FeedingSchedule> getScheduleList();
}

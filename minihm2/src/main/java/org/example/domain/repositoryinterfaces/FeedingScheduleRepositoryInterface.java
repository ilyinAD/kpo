package org.example.domain.repositoryinterfaces;

import org.example.domain.models.FeedingSchedule;

import java.util.Optional;

public interface FeedingScheduleRepositoryInterface {
    void add(FeedingSchedule schedule);
    void update(FeedingSchedule schedule);
    void delete(FeedingSchedule schedule);
    Optional<FeedingSchedule> getByID(String scheduleID);
}

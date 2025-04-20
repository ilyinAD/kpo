package org.example.application.services;

import org.example.domain.models.FeedingSchedule;
import org.example.domain.repositoryinterfaces.FeedingScheduleRepositoryInterface;
import org.example.infrastructure.repositories.FeedingScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedingOrganizationService {
    private final FeedingScheduleRepositoryInterface scheduleRepository;
    @Autowired
    public FeedingOrganizationService(FeedingScheduleRepositoryInterface scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public void addFeedingSchedule(FeedingSchedule schedule) {
        scheduleRepository.save(schedule);
    }

    public void markFeedingDone(FeedingSchedule schedule) {
        schedule.markAsCompleted();
        scheduleRepository.save(schedule);
    }
}

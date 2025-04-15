package org.example.services;

import org.example.domain.FeedingSchedule;
import org.example.repositories.FeedingScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FeedingOrganizationService {
    private final FeedingScheduleRepository scheduleRepository;
    @Autowired
    public FeedingOrganizationService(FeedingScheduleRepository scheduleRepository) {
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

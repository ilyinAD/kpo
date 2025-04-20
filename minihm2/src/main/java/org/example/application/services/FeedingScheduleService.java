package org.example.application.services;

import org.example.domain.models.FeedingSchedule;
import org.example.domain.repositoryinterfaces.FeedingScheduleRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedingScheduleService {
    final FeedingScheduleRepositoryInterface feedingScheduleRepository;
    @Autowired
    FeedingScheduleService(FeedingScheduleRepositoryInterface feedingScheduleRepository) {
        this.feedingScheduleRepository = feedingScheduleRepository;
    }

    public void save(FeedingSchedule schedule) {
        boolean exists = feedingScheduleRepository.getByID(schedule.getId()).isPresent();
        if (!exists) {
            feedingScheduleRepository.add(schedule);
        } else {
            feedingScheduleRepository.update(schedule);
        }
    }
}

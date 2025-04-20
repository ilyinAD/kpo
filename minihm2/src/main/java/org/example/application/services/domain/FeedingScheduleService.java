package org.example.application.services.domain;

import org.example.domain.models.Enclosure;
import org.example.domain.models.FeedingSchedule;
import org.example.domain.repositoryinterfaces.FeedingScheduleRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedingScheduleService {
    final FeedingScheduleRepositoryInterface feedingScheduleRepository;
    @Autowired
    FeedingScheduleService(FeedingScheduleRepositoryInterface feedingScheduleRepository) {
        this.feedingScheduleRepository = feedingScheduleRepository;
    }

    public FeedingSchedule save(FeedingSchedule schedule) {
        boolean exists = feedingScheduleRepository.getByID(schedule.getId()).isPresent();
        if (!exists) {
            return feedingScheduleRepository.add(schedule);
        } else {
            return feedingScheduleRepository.update(schedule);
        }
    }
    public FeedingSchedule delete(FeedingSchedule schedule) {
        return feedingScheduleRepository.delete(schedule);
    }
    public Optional<FeedingSchedule> getByID(String scheduleID) {
        return feedingScheduleRepository.getByID(scheduleID);
    }

    public List<FeedingSchedule> getSchedules() {
        return feedingScheduleRepository.getScheduleList();
    }
}

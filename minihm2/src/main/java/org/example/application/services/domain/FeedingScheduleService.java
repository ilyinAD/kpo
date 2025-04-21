package org.example.application.services.domain;

import org.example.application.interfaces.domain.IAnimalService;
import org.example.application.interfaces.domain.IFeedingScheduleService;
import org.example.domain.models.FeedingSchedule;
import org.example.domain.repositoryinterfaces.AnimalRepositoryInterface;
import org.example.domain.repositoryinterfaces.FeedingScheduleRepositoryInterface;
import org.example.exceptions.EnclosureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedingScheduleService implements IFeedingScheduleService {
    final FeedingScheduleRepositoryInterface feedingScheduleRepository;
    final IAnimalService animalService;
    @Autowired
    FeedingScheduleService(FeedingScheduleRepositoryInterface feedingScheduleRepository, IAnimalService animalService) {
        this.feedingScheduleRepository = feedingScheduleRepository;
        this.animalService = animalService;
    }

    @Override
    public FeedingSchedule save(FeedingSchedule schedule) {
        if (animalService.getByID(schedule.getAnimalID()).isEmpty()) {
            throw new IllegalArgumentException("animal with id " + schedule.getAnimalID() + " does not exist");
        }

        boolean exists = feedingScheduleRepository.getByID(schedule.getId()).isPresent();
        if (!exists) {
            return feedingScheduleRepository.add(schedule);
        } else {
            return feedingScheduleRepository.update(schedule);
        }
    }
    @Override
    public FeedingSchedule delete(FeedingSchedule schedule) {
        return feedingScheduleRepository.delete(schedule);
    }
    @Override
    public Optional<FeedingSchedule> getByID(String scheduleID) {
        return feedingScheduleRepository.getByID(scheduleID);
    }

    @Override
    public List<FeedingSchedule> getSchedules() {
        return feedingScheduleRepository.getScheduleList();
    }
}

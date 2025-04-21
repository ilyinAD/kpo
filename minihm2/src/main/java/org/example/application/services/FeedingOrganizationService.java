package org.example.application.services;

import org.example.application.events.FeedingTimeEvent;
import org.example.application.interfaces.IFeedingOrganizationService;
import org.example.application.services.domain.AnimalService;
import org.example.application.services.domain.FeedingScheduleService;
import org.example.domain.models.Animal;
import org.example.domain.models.FeedingSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class FeedingOrganizationService implements IFeedingOrganizationService {
    private final FeedingScheduleService scheduleService;
    private final AnimalService animalService;
    private final ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    public FeedingOrganizationService(FeedingScheduleService scheduleService, AnimalService animalService, ApplicationEventPublisher applicationEventPublisher) {
        this.scheduleService = scheduleService;
        this.animalService = animalService;
        this.applicationEventPublisher = applicationEventPublisher;
    }
    @Override
    public void executeFeedingSchedule(String feedingScheduleID) {
        FeedingSchedule feedingSchedule = scheduleService.getByID(feedingScheduleID)
                .orElseThrow(() -> new IllegalArgumentException("Schedule with id " + feedingScheduleID + " wasn't found"));
        Animal animal = animalService.getByID(feedingSchedule.getAnimalID())
                .orElseThrow(() -> new IllegalArgumentException("Animal with id " + feedingSchedule.getAnimalID() + " wasn't found"));
        animal.feed();
        animalService.save(animal);
        applicationEventPublisher.publishEvent(new FeedingTimeEvent(feedingScheduleID));
    }

    @Override
    public void addFeedingSchedule(FeedingSchedule schedule) {
        scheduleService.save(schedule);
    }

    @Override
    public void markFeedingDone(FeedingSchedule schedule) {
        schedule.markAsCompleted();
        scheduleService.save(schedule);
    }
}

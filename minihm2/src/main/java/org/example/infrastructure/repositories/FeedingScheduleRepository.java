package org.example.infrastructure.repositories;

import org.example.domain.models.FeedingSchedule;
import org.example.domain.repositoryinterfaces.FeedingScheduleRepositoryInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class FeedingScheduleRepository implements FeedingScheduleRepositoryInterface {
    private List<FeedingSchedule> scheduleList = new ArrayList<>();

    public void update(FeedingSchedule schedule) {
        for (int i = 0; i < scheduleList.size(); ++i) {
            if (Objects.equals(scheduleList.get(i).getId(), schedule.getId())) {
                scheduleList.set(i, schedule);
                return;
            }
        }
    }
    public void add(FeedingSchedule schedule) {
        scheduleList.add(schedule);
    }
    public void delete(FeedingSchedule schedule) {
        scheduleList.remove(schedule);
    }
    public Optional<FeedingSchedule> getByID(String scheduleID) {
        return scheduleList.stream().
                filter(schedule -> Objects.equals(schedule.getId(), scheduleID))
                .findFirst();
    }
}

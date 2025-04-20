package org.example.infrastructure.repositories;

import lombok.Getter;
import org.example.domain.models.FeedingSchedule;
import org.example.domain.repositoryinterfaces.FeedingScheduleRepositoryInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@Getter
public class FeedingScheduleRepository implements FeedingScheduleRepositoryInterface {
    private List<FeedingSchedule> scheduleList = new ArrayList<>();

    public FeedingSchedule update(FeedingSchedule schedule) {
        for (int i = 0; i < scheduleList.size(); ++i) {
            if (Objects.equals(scheduleList.get(i).getId(), schedule.getId())) {
                scheduleList.set(i, schedule);
                return scheduleList.get(i);
            }
        }
        throw new IllegalStateException("no feedingSchedule in list");
    }
    public FeedingSchedule add(FeedingSchedule schedule) {
        scheduleList.add(schedule);
        return schedule;
    }
    public FeedingSchedule delete(FeedingSchedule schedule) {
        scheduleList.remove(schedule);
        return schedule;
    }
    public Optional<FeedingSchedule> getByID(String scheduleID) {
        return scheduleList.stream().
                filter(schedule -> Objects.equals(schedule.getId(), scheduleID))
                .findFirst();
    }
}

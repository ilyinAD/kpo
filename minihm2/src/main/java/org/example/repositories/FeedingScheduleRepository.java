package org.example.repositories;

import org.example.domain.FeedingSchedule;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Component
public class FeedingScheduleRepository {
    private List<FeedingSchedule> scheduleList = new ArrayList<>();
    public void save(FeedingSchedule schedule) {
        boolean exists = scheduleList.stream()
                .anyMatch(el -> Objects.equals(el.getId(), schedule.getId()));
        if (!exists) {
            add(schedule);
        } else {
            update(schedule);
        }
    }
    private void update(FeedingSchedule schedule) {
        for (int i = 0; i < scheduleList.size(); ++i) {
            if (Objects.equals(scheduleList.get(i).getId(), schedule.getId())) {
                scheduleList.set(i, schedule);
                return;
            }
        }
    }
    private void add(FeedingSchedule schedule) {
        scheduleList.add(schedule);
    }
    public void delete(FeedingSchedule schedule) {
        scheduleList.remove(schedule);
    }
}

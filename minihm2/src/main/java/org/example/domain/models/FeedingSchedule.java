package org.example.domain.models;

import lombok.Getter;

import java.time.LocalTime;
import java.util.Objects;
import java.util.function.Supplier;
@Getter
public class FeedingSchedule {
    private Animal animal;
    private LocalTime feedingTime;
    private String foodType;
    private boolean isComplete;
    private String id;

    public FeedingSchedule(Animal animal, LocalTime feedingTime, String foodType) {
        this.animal = animal;
        this.feedingTime = feedingTime;
        this.foodType = foodType;
        this.isComplete = false;
        this.id = IdGenerator.generateID();
    }

    public FeedingSchedule(Animal animal, LocalTime feedingTime, String foodType, Supplier<String> generateID) {
        this.animal = animal;
        this.feedingTime = feedingTime;
        this.foodType = foodType;
        this.isComplete = false;
        this.id = generateID.get();
    }
    public void markAsCompleted() {
        this.isComplete = true;
    }

    public void changeSchedule(LocalTime newTime, String newFoodType) {
        if (Objects.equals(newFoodType, "")) {
            newFoodType = this.foodType;
        }

        this.feedingTime = newTime;
        this.foodType = newFoodType;
    }
}

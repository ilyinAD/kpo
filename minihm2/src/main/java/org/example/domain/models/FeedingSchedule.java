package org.example.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.function.Supplier;
@Getter
public class FeedingSchedule {
    private String animalID;
    private LocalDate feedingTime;
    private String foodType;
    private boolean isComplete;
    private String id;
    @JsonCreator

    public FeedingSchedule(@JsonProperty("animalID") String animalID,
                           @JsonProperty("feedingTime") LocalDate feedingTime,
                           @JsonProperty("foodType") String foodType) {
        this.animalID = animalID;
        this.feedingTime = feedingTime;
        this.foodType = foodType;
        this.isComplete = false;
        this.id = IdGenerator.generateID();
    }

    public FeedingSchedule(String animalID, LocalDate feedingTime, String foodType, Supplier<String> generateID) {
        this.animalID = animalID;
        this.feedingTime = feedingTime;
        this.foodType = foodType;
        this.isComplete = false;
        this.id = generateID.get();
    }
    public void markAsCompleted() {
        this.isComplete = true;
    }

    public void changeSchedule(LocalDate newTime, String newFoodType) {
        if (Objects.equals(newFoodType, "")) {
            newFoodType = this.foodType;
        }

        this.feedingTime = newTime;
        this.foodType = newFoodType;
    }
}

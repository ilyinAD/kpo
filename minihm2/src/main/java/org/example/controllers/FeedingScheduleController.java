package org.example.controllers;

import lombok.Getter;
import org.example.application.interfaces.domain.IFeedingScheduleService;
import org.example.application.services.domain.FeedingScheduleService;
import org.example.domain.models.FeedingSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feeding")
public class FeedingScheduleController {
    private final IFeedingScheduleService feedingScheduleService;
    @Autowired
    FeedingScheduleController(IFeedingScheduleService feedingScheduleService) {
        this.feedingScheduleService = feedingScheduleService;
    }

    @PostMapping
    public ResponseEntity<?> addSchedule(@RequestBody FeedingSchedule schedule) {
        try {
            FeedingSchedule addedSchedule = feedingScheduleService.save(schedule);
            return ResponseEntity.ok(addedSchedule);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @GetMapping
    public ResponseEntity<?> getSchedule() {
        try {
            return ResponseEntity.ok(feedingScheduleService.getSchedules());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }
}

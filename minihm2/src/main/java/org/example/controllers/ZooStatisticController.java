package org.example.controllers;

import org.example.application.services.ZooStatisticService;
import org.example.domain.models.Enclosure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class ZooStatisticController {
    private final ZooStatisticService zooStatisticService;
    @Autowired
    ZooStatisticController(ZooStatisticService zooStatisticService) {
        this.zooStatisticService = zooStatisticService;
    }

    @GetMapping("/animals")
    public ResponseEntity<Long> getAnimalAmount() {
        try {
            return ResponseEntity.ok(zooStatisticService.countAnimals());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/empty_enclosures")
    public ResponseEntity<List<Enclosure>> getEmptyEnclosures() {
        try {
            return ResponseEntity.ok(zooStatisticService.getEmptyEnclosures());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

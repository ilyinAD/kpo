package org.example.controllers;

import org.example.application.interfaces.domain.IEnclosureService;
import org.example.application.services.domain.AnimalService;
import org.example.application.services.domain.EnclosureService;
import org.example.domain.models.Animal;
import org.example.domain.models.Enclosure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enclosures")
public class EnclosureController {
    private final IEnclosureService enclosureService;
    @Autowired
    EnclosureController(IEnclosureService enclosureService) {
        this.enclosureService = enclosureService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEnclosureByID(@PathVariable String id) {
        try {
            Optional<Enclosure> enclosure = enclosureService.getByID(id);
            return enclosure.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }
    @GetMapping
    public ResponseEntity<List<Enclosure>> getEnclosures() {
        return ResponseEntity.ok(enclosureService.getEnclosures());
    }

    @PostMapping
    public ResponseEntity<?> addAnimal(@RequestBody Enclosure enclosure) {
        try {
            Enclosure addedEnclosure = enclosureService.save(enclosure);
            return ResponseEntity.ok(addedEnclosure);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnimal(@PathVariable String id) {
        try {
            Enclosure deletedEnclosure = enclosureService.deleteByID(id);
            return ResponseEntity.ok(deletedEnclosure);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }
}

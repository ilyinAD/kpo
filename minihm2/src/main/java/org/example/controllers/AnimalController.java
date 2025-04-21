package org.example.controllers;
import org.example.application.interfaces.domain.IAnimalService;
import org.example.domain.models.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/animals")
public class AnimalController {
    private final IAnimalService animalService;
    @Autowired
    AnimalController(IAnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAnimalById(@PathVariable String id) {
        try {
            Optional<Animal> animal = animalService.getByID(id);
            return animal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }
    @GetMapping
    public ResponseEntity<List<Animal>> getAnimals() {
       return ResponseEntity.ok(animalService.getAnimals());
    }

    @PostMapping
    public ResponseEntity<?> addAnimal(@RequestBody Animal animal) {
        try {
            Animal addedAnimal = animalService.save(animal);
            return ResponseEntity.ok(addedAnimal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnimal(@PathVariable String id) {
        try {
            Animal deletedAnimal = animalService.deleteByID(id);
            return ResponseEntity.ok(deletedAnimal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }
}

package org.example.controllers;

import org.example.application.interfaces.IAnimalTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class AnimalTransferController {
    private final IAnimalTransferService animalTransferService;
    @Autowired
    AnimalTransferController(IAnimalTransferService animalTransferService) {
        this.animalTransferService = animalTransferService;
    }

    @PostMapping("/{animalID}/{enclosureID}")
    public ResponseEntity<?> transfer(@PathVariable String animalID, @PathVariable String enclosureID) {
        try {
            animalTransferService.transferAnimal(animalID, enclosureID);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }
}

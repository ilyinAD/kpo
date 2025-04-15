package org.example;

import org.example.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Main implements CommandLineRunner {
    private final AnimalRepository animalRepository;
    @Autowired
    public Main(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }
    @Override
    public void run(String... args) {
        System.out.println("IN MAIN");
        animalRepository.f();
    }
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
package org.example;

import org.example.controllers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import java.util.Scanner;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private final TerminalController terminalController;
    @Autowired
    public Main(TerminalController terminalController) {
        this.terminalController = terminalController;
    }
    @Override
    public void run(String... args) {
        System.out.println("IN MAIN");
        terminalController.start(new Scanner(System.in), "./", "./"); // закоментить если тестируем(
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
package org.example.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Scanner;

@SpringBootTest
public class TerminalControllerTest {
    @Autowired
    private TerminalController terminalController;
    @Test
    public void startTest() {
        Scanner scanner = new Scanner("csv\nyaml\n4\n" +
                "5\n6\n7\n2020-10-10\n2024-10-10\n1\nмакс1\n1000\n" +
                "2\nдоход\nстажка\n3\nартем\n100\n2024-12-12\nhz\nработа\n0\n");
        terminalController.start(scanner);
    }
}

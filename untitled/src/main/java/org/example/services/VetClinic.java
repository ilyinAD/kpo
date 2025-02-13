package org.example.services;

import org.example.domain.Animal;
import org.example.interfaces.Clinic;
import org.springframework.stereotype.Service;

@Service
public class VetClinic implements Clinic {
    public boolean checkHealth(Animal animal) {
        return (Math.random() * 10) < 8;
    }
}

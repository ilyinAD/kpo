package org.example.services;

import org.example.domain.Animal;
import org.example.domain.Herbivore;
import org.example.interfaces.Clinic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ZooService {
    private List<Animal> animals = new ArrayList<>();
    private Clinic vetClinic;

    @Autowired
    public ZooService(Clinic vetClinic) {
        this.vetClinic = vetClinic;
    }
    public Optional<String> addAnimal(Animal animal) {
        if (vetClinic.checkHealth(animal)) {
            animals.add(animal);
            return Optional.empty();
        } else {
            return Optional.of("Ошибка добавления животного: проверка здоровья не пройдена.");
        }
    }

    public int getGeneralFood() {
        return animals.stream().mapToInt(Animal::getFood).sum();

    }

    public List<Animal> getContactAnimals() {
        return animals.stream()
                .filter(a -> a instanceof Herbivore && ((Herbivore) a).getKindness() > 5).toList();
    }
}

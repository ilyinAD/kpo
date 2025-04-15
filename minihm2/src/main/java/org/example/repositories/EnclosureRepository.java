package org.example.repositories;

import org.example.domain.Enclosure;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Component
public class EnclosureRepository {
    private List<Enclosure> enclosures = new ArrayList<>();

    public void save(Enclosure enclosure) {
        boolean exists = enclosures.stream()
                .anyMatch(el -> Objects.equals(el.getId(), enclosure.getId()));
        if (!exists) {
            save(enclosure);
        } else {
            update(enclosure);
        }
    }

    private void update(Enclosure enclosure) {
        for (int i = 0; i < enclosures.size(); ++i) {
            if (Objects.equals(enclosures.get(i).getId(), enclosure.getId())) {
                enclosures.set(i, enclosure);
                return;
            }
        }
    }
    private void add(Enclosure enclosure) {
        enclosures.add(enclosure);
    }

    public void delete(Enclosure enclosure) {
        enclosures.remove(enclosure);
    }

    public Optional<Enclosure> getByID(String id) {
        return enclosures.stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .findFirst();
    }
}

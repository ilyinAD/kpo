package org.example.infrastructure.repositories;

import org.example.domain.models.Enclosure;
import org.example.domain.repositoryinterfaces.EnclosureRepositoryInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Component
public class EnclosureRepository implements EnclosureRepositoryInterface {
    private List<Enclosure> enclosures = new ArrayList<>();

    public void update(Enclosure enclosure) {
        for (int i = 0; i < enclosures.size(); ++i) {
            if (Objects.equals(enclosures.get(i).getId(), enclosure.getId())) {
                enclosures.set(i, enclosure);
                return;
            }
        }
    }
    public void add(Enclosure enclosure) {
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

package org.example.infrastructure.repositories;

import lombok.Getter;
import org.example.domain.models.Enclosure;
import org.example.domain.repositoryinterfaces.EnclosureRepositoryInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Component
@Getter
public class EnclosureRepository implements EnclosureRepositoryInterface {
    private final List<Enclosure> enclosures = new ArrayList<>();

    public Enclosure update(Enclosure enclosure) {
        for (int i = 0; i < enclosures.size(); ++i) {
            if (Objects.equals(enclosures.get(i).getId(), enclosure.getId())) {
                enclosures.set(i, enclosure);
                return enclosures.get(i);
            }
        }
        throw new IllegalStateException("no enclosure in list");
    }
    public Enclosure add(Enclosure enclosure) {
        enclosures.add(enclosure);
        return enclosure;
    }

    public Enclosure delete(Enclosure enclosure) {
        enclosures.remove(enclosure);
        return enclosure;
    }

    public Optional<Enclosure> getByID(String id) {
        return enclosures.stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .findFirst();
    }
}

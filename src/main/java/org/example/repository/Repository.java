package org.example.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {
    void add(T item);
    Optional<T> findById(ID id);
    List<T> findAll();
    void remove(ID id);
    boolean update(ID id, T updatedEntity);
}

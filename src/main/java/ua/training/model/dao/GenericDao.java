package ua.training.model.dao;

import java.util.List;
import java.util.Optional;

/**
 * Created by Денис on 14.12.2016.
 */
public interface GenericDao<E> {
    Optional<E> find(int id);
    List<E> findAll();
    void create(E e);
    void update(E e);
    void delete(int id);
}

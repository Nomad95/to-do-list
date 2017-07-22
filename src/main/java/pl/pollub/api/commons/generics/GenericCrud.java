package pl.pollub.api.commons.generics;

import java.util.List;

public interface GenericCrud <T> {
    T save(T object);

    boolean remove(T object);

    T findOne(Integer id);

    List<T> findAll();
}

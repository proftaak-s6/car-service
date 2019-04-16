package nl.fontysproject.government.api.service.Interfaces;

import java.util.List;

public interface BaseService<T> {
    T getById(long id);
    List<T> getAll();
    T create(T model);
    T update(T model);
    boolean delete(long id);
}

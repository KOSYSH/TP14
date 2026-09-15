package ma.projet.dao;

import ma.projet.bean.Identifiable;

import java.util.List;

public interface Dao<T extends Identifiable> {
    void create(T obj);

    T update(T obj);

    boolean delete(int id);

    T findById(int id);

    List<T> findAll();
}

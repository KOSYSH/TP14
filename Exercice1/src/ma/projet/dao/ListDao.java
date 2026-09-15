package ma.projet.dao;

import ma.projet.bean.Identifiable;

import java.util.ArrayList;
import java.util.List;

public class ListDao<T extends Identifiable> implements Dao<T> {
    private final List<T> elements = new ArrayList<T>();

    @Override
    public void create(T obj) {
        verifierObjet(obj);
        if (findById(obj.getId()) != null) {
            throw new IllegalArgumentException("Un objet avec cet ID existe deja");
        }
        elements.add(obj);
    }

    @Override
    public T update(T obj) {
        verifierObjet(obj);
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getId() == obj.getId()) {
                elements.set(i, obj);
                return obj;
            }
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        if (id <= 0) {
            return false;
        }
        return elements.removeIf(element -> element.getId() == id);
    }

    @Override
    public T findById(int id) {
        if (id <= 0) {
            return null;
        }
        return elements.stream()
                .filter(element -> element.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<T>(elements);
    }

    private void verifierObjet(T obj) {
        if (obj == null) {
            throw new IllegalArgumentException("L'objet ne peut pas etre null");
        }
    }
}

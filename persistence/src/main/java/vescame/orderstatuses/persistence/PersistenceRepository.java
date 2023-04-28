package vescame.orderstatuses.persistence;

import java.util.Collection;

public interface PersistenceRepository<T extends Number, E extends PersistableEntity<T>> {

    Collection<E> getAll();
    E add(E entity);
    E getById(T id);
    void update(E entity);
    void delete(E entity);
}

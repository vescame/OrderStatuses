package vescame.orderstatuses.persistence.storage;

import vescame.orderstatuses.persistence.storage.domain.PersistableEntity;

import java.util.Collection;

public interface PersistableRawStorage<T extends Number, E extends PersistableEntity<T>> {

    Collection<E> getAll();
    E add(E entity);
    E getById(T id);
    void update(E entity);
    void delete(E entity);
}

package vescame.orderstatuses.persistence.storage.map;

import org.springframework.stereotype.Component;
import vescame.orderstatuses.persistence.storage.PersistableRawStorage;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class HashMapLongRawStorage<T extends LongPersistableEntity> implements PersistableRawStorage<Long, T> {

    private final Map<Long, T> STORAGE;

    public HashMapLongRawStorage() {
        this.STORAGE = new ConcurrentHashMap<>();
    }

    @Override
    public T getById(Long id) {
        return STORAGE.get(id);
    }

    @Override
    public Collection<T> getAll() {
        return STORAGE.values();
    }

    @Override
    public T add(T entity) {
        entity.setId(getNextId());

        if (exists(entity))
            throw new IllegalArgumentException(String.format("entity (%d) already exists in the storage", entity.getId()));

        STORAGE.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void update(T entity) {
        if (!exists(entity))
            throw new IllegalArgumentException(String.format("entity (%d) doesn't exist in the storage", entity.getId()));

        STORAGE.put(entity.getId(), entity);
    }

    @Override
    public void delete(T entity) {
        if (!STORAGE.remove(entity.getId(), entity)) {
            throw new IllegalArgumentException(String.format("entity (%d) doesn't exist in the storage", entity.getId()));
        }
    }

    private boolean exists(T entity) {
        return STORAGE.containsKey(entity.getId());
    }

    private Long getNextId() {
        synchronized (STORAGE) {
            if (STORAGE.isEmpty()) return 1L;

            return STORAGE.keySet()
                    .stream()
                    .max(Comparator.naturalOrder())
                    .get() + 1;
        }
    }
}

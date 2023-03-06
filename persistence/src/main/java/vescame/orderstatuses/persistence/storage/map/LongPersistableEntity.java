package vescame.orderstatuses.persistence.storage.map;

import vescame.orderstatuses.persistence.storage.domain.PersistableEntity;

public interface LongPersistableEntity extends PersistableEntity<Long>, Cloneable {

    @Override
    Long getId();

    @Override
    void setId(Long id);
}

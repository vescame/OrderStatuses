package vescame.orderstatuses.persistence.storage.domain;

public interface IdentifiableEntity<T extends Number> {

    T getId();
    void setId(T id);
}

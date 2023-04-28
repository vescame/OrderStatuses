package vescame.orderstatuses.persistence;

public interface IdentifiableEntity<T extends Number> {

    T getId();
    void setId(T id);
}

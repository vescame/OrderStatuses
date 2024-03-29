package vescame.orderstatuses.inmemory.hashmap;

import vescame.orderstatuses.persistence.PersistableEntity;

public class DummyEntity implements PersistableEntity<Long> {
    private Long id;
    private String name;

    public DummyEntity(Long id) {
        this.id = id;
        this.name = "default";
    }

    public DummyEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @ Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

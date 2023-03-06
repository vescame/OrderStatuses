package vescame.orderstatuses.persistence.order;

import vescame.orderstatuses.entity.item.Item;
import vescame.orderstatuses.persistence.storage.map.LongPersistableEntity;

public class OrderLineEntity implements LongPersistableEntity {
    private Long id;
    private Item item;
    private Integer quantity;

    public OrderLineEntity(Long id, Item item, Integer quantity) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
    }

    public OrderLineEntity(Item item, Integer quantity) {
        this.id = null;
        this.item = item;
        this.quantity = quantity;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

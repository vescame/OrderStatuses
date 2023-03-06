package vescame.orderstatuses.entity.order;

import vescame.orderstatuses.entity.item.Item;

public record OrderLine(
        Long id,
        Item item,
        Integer quantity
) {

    public OrderLine(Long itemId, Integer quantity) {
        this(null,  new Item(itemId), quantity);
    }
}

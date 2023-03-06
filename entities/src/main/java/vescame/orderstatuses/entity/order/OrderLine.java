package vescame.orderstatuses.entity.order;

import vescame.orderstatuses.entity.item.Item;

public record OrderLine(
        Long id,
        Order order,
        Item item,
        Integer quantity
) {

    public OrderLine(Long itemId) {
        this(null, null, new Item(itemId), null);
    }
}

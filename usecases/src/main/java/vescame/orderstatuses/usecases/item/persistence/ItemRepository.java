package vescame.orderstatuses.usecases.item.persistence;

import vescame.orderstatuses.entity.item.Item;

public interface ItemRepository {

    boolean existsItemById(Long itemId);
    Item getItemById(Long itemId);
}

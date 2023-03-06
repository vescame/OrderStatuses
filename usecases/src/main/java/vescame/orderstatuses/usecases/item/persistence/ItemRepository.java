package vescame.orderstatuses.usecases.item.persistence;

import vescame.orderstatuses.entity.item.Item;

import java.util.Collection;

public interface ItemRepository {

    boolean existsItemById(Long itemId);
    Item getItemById(Long itemId);

    Collection<Item> listAll();
}

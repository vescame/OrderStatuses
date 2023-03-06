package vescame.orderstatuses.usecases.service;

import vescame.orderstatuses.entity.item.Item;
import java.util.Collection;

public interface ItemService {

    Collection<Item> getAll();
}

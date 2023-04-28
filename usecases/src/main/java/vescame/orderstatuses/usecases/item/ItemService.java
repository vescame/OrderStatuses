package vescame.orderstatuses.usecases.item;

import vescame.orderstatuses.entity.item.Item;
import java.util.Collection;

public interface ItemService {

    Collection<Item> getAll();
}

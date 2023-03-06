package vescame.orderstatuses.usecases.service.Impl;

import vescame.orderstatuses.entity.item.Item;
import vescame.orderstatuses.usecases.item.persistence.ItemRepository;
import vescame.orderstatuses.usecases.service.ItemService;
import java.util.Collection;

public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Collection<Item> getAll() {
        return itemRepository.listAll();
    }
}

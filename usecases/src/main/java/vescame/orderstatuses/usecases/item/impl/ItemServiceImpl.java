package vescame.orderstatuses.usecases.item.impl;

import org.springframework.stereotype.Service;
import vescame.orderstatuses.entity.item.Item;
import vescame.orderstatuses.usecases.item.persistence.ItemRepository;
import vescame.orderstatuses.usecases.item.ItemService;
import java.util.Collection;

@Service
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

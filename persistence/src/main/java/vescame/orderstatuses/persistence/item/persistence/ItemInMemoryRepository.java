package vescame.orderstatuses.persistence.item.persistence;

import org.springframework.stereotype.Repository;
import vescame.orderstatuses.entity.item.Item;
import vescame.orderstatuses.persistence.item.ItemEntity;
import vescame.orderstatuses.persistence.storage.map.HashMapLongRawStorage;
import vescame.orderstatuses.usecases.item.persistence.ItemRepository;
import java.util.Collection;

@Repository
public class ItemInMemoryRepository implements ItemRepository {

    private final HashMapLongRawStorage<ItemEntity> STORAGE;

    public ItemInMemoryRepository (
            HashMapLongRawStorage<ItemEntity> hashMapLongRawStorage
    ) {
        this.STORAGE = hashMapLongRawStorage;
    }

    @Override
    public Item getItemById(Long itemId) {
        ItemEntity entity = STORAGE.getById(itemId);
        return new Item(entity.getId(), entity.getName(), entity.getDetails(), entity.getPrice());
    }

    @Override
    public Collection<Item> listAll() {
        return STORAGE.getAll()
                .stream()
                .map(itemEntity -> new Item(
                        itemEntity.getId(),
                        itemEntity.getName(),
                        itemEntity.getDetails(),
                        itemEntity.getPrice()
                ))
                .toList();
    }

    @Override
    public boolean existsItemById(Long itemId) {
        return STORAGE.getById(itemId) != null;
    }

}

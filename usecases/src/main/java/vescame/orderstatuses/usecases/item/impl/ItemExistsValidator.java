package vescame.orderstatuses.usecases.item.impl;

import org.springframework.stereotype.Component;
import vescame.orderstatuses.usecases.item.persistence.ItemRepository;
import vescame.orderstatuses.usecases.item.ItemValidator;
import vescame.orderstatuses.usecases.validation.IdValidator;

@Component
public class ItemExistsValidator implements ItemValidator {

    private final ItemRepository itemRepository;
    private final IdValidator idValidator;

    public ItemExistsValidator(ItemRepository itemRepository, IdValidator idValidator) {
        this.itemRepository = itemRepository;
        this.idValidator = idValidator;
    }

    @Override
    public boolean isItemValid(Long itemId) {
        if (!idValidator.isValidId(itemId)) throw new IllegalArgumentException("an itemId cannot be < 1");

        return itemExists(itemId);
    }

    private boolean itemExists(Long itemId) {
        return itemRepository.existsItemById(itemId);
    }
}

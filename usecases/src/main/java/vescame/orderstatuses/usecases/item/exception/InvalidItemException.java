package vescame.orderstatuses.usecases.item.exception;

import vescame.orderstatuses.usecases.common.exception.NotFoundException;

public class InvalidItemException extends NotFoundException {

    public InvalidItemException(Long itemId) {
        super(String.format("Item (%d) is not a valid item. Couldn't be found.", itemId));
    }
}

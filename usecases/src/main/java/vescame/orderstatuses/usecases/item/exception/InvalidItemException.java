package vescame.orderstatuses.usecases.item.exception;

public class InvalidItemException extends RuntimeException {

    public InvalidItemException(Long itemId) {
        super(String.format("Item (%d) is not a valid item. Couldn't be found.", itemId));
    }
}

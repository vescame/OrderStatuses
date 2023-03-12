package vescame.orderstatuses.usecases.order.exception;

import vescame.orderstatuses.usecases.common.exception.NotFoundException;

public class InvalidOrderException extends NotFoundException {

    public InvalidOrderException(Long orderId) {
        super(String.format("Order (%d) is not a valid order. Couldn't be found.", orderId));
    }
}

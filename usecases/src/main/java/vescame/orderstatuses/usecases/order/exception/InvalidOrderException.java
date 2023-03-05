package vescame.orderstatuses.usecases.order.exception;

public class InvalidOrderException extends RuntimeException {

    public InvalidOrderException(Long orderId) {
        super(String.format("Order (%d) is not a valid order. Couldn't be found.", orderId));
    }
}

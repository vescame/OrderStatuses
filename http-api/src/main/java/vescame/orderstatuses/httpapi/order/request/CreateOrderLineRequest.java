package vescame.orderstatuses.httpapi.order.request;

public record CreateOrderLineRequest(
        Long itemId,
        Integer quantity
) { /* empty */ }

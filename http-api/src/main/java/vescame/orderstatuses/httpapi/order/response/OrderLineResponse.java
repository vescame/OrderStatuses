package vescame.orderstatuses.httpapi.order.response;

import vescame.orderstatuses.httpapi.item.ItemResponse;

public record OrderLineResponse(
        ItemResponse item,
        Integer quantity
) { /* empty */ }

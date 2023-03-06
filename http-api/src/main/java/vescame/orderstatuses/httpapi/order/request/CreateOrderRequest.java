package vescame.orderstatuses.httpapi.order.request;

import java.util.Collection;

public record CreateOrderRequest(
        Long customerId,
        Collection<CreateOrderLineRequest> orderLines
) { /* empty */ }

package vescame.orderstatuses.httpapi.order.response;

import vescame.orderstatuses.entity.order.OrderStatus;

import java.util.Collection;

public record OrderResponse(
        Long id,
        Long customerId,
        Collection<OrderLineResponse> orderLines,
        OrderStatus status
) { /* empty */ }

package vescame.orderstatuses.httpapi.order.response;

import vescame.orderstatuses.entity.order.OrderStatus;

import java.math.BigDecimal;
import java.util.Collection;

public record OrderResponse(
        Long id,
        Long customerId,
        Collection<OrderLineResponse> orderLines,
        OrderStatus status,
        BigDecimal total
) { /* empty */ }

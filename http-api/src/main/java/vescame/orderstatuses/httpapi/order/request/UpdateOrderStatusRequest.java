package vescame.orderstatuses.httpapi.order.request;

import vescame.orderstatuses.entity.order.OrderStatus;

public record UpdateOrderStatusRequest(OrderStatus status) { /* empty */ }

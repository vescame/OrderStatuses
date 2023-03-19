package vescame.orderstatuses.httpapi.order.request;

import jakarta.validation.constraints.NotNull;
import vescame.orderstatuses.entity.order.OrderStatus;

public record UpdateOrderStatusRequest(@NotNull(message = "status cannot be null") OrderStatus status) { /* empty */ }

package vescame.orderstatuses.httpapi.order.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateOrderLineRequest(
        @NotNull(message = "itemId cannot be null") Long itemId,
        @NotNull(message = "quantity cannot be null")
        @Positive(message = "quantity cannot be less than 0")
        Integer quantity
) { /* empty */ }

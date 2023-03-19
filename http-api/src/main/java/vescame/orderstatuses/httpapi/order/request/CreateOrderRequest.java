package vescame.orderstatuses.httpapi.order.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Collection;

public record CreateOrderRequest(
        @NotNull(message = "customerId cannot be null") Long customerId,
        @Valid @Size(min = 1, message = "the orderLines must have at least one item")
        Collection<CreateOrderLineRequest> orderLines
) { /* empty */ }

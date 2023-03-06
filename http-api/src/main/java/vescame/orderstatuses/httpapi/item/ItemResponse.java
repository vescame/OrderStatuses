package vescame.orderstatuses.httpapi.item;

import java.math.BigDecimal;

public record ItemResponse(
        Long id,
        String name,
        String details,
        BigDecimal price
) { /* empty */ }

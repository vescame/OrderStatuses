package vescame.orderstatuses.httpapi.item;

import java.math.BigDecimal;

public record ItemResponse(
        String name,
        BigDecimal price
) { /* empty */ }

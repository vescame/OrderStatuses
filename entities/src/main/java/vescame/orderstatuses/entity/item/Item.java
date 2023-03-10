package vescame.orderstatuses.entity.item;

import java.math.BigDecimal;

public record Item(
        Long id,
        String name,
        String details,
        BigDecimal price
) {

    public Item(Long id) {
        this(id, null, null, null);
    }
}

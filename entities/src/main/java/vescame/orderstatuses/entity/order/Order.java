package vescame.orderstatuses.entity.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;

public record Order(
        Long id,
        Long customerId,
        Collection<OrderLine> orderLines,
        BigDecimal orderTotalAmount,
        OrderStatus status,
        LocalDateTime createDate,
        LocalDateTime updateDate
) {

    public Order(Long customerId, Collection<OrderLine> orderLines) {
        this(null, customerId, orderLines, null, null, null, null);
    }
}

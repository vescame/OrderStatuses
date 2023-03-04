package vescame.orderstatuses.entity.order;

import vescame.orderstatuses.entity.customer.Customer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;

public record Order(
        Long id,
        Customer customer,
        Collection<OrderLine> orderLines,
        BigDecimal orderTotalAmount,
        OrderStatus status,
        LocalDateTime createDate,
        LocalDateTime updateDate
) { /* empty */ }

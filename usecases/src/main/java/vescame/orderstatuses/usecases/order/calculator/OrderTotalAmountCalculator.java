package vescame.orderstatuses.usecases.order.calculator;

import org.springframework.stereotype.Component;
import vescame.orderstatuses.entity.order.OrderLine;

import java.math.BigDecimal;
import java.util.Collection;

@Component
public class OrderTotalAmountCalculator {

    public BigDecimal calculateTotal(Collection<OrderLine> orderLines) {
        return orderLines
                .stream()
                .map( line -> {
                    var quantityAsBigDecimal = BigDecimal.valueOf(line.quantity());
                    return line.item().price().multiply(quantityAsBigDecimal);
                })
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}

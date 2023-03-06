package vescame.orderstatuses.usecases.order.calculator;

import org.junit.jupiter.api.Test;
import vescame.orderstatuses.entity.item.Item;
import vescame.orderstatuses.entity.order.OrderLine;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTotalAmountCalculatorTest {

    private final OrderTotalAmountCalculator calculator = new OrderTotalAmountCalculator();

    @Test
    public void shouldCalculateTotalOfAnOrder() {
        var orderLines = List.of(
                new OrderLine(
                        1L,
                        new Item(
                                1L,
                                "pencil",
                                null,
                                BigDecimal.TEN
                        ),
                        2
                )
        );

        var expected = BigDecimal.valueOf(20);

        var actual = calculator.calculateTotal(orderLines);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnZeroWhenNoOrderLines() {
        Collection<OrderLine> orderLines = Collections.emptyList();

        var expected = BigDecimal.ZERO;

        var actual = calculator.calculateTotal(orderLines);

        assertEquals(expected, actual);
    }
}
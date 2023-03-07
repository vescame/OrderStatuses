package vescame.orderstatuses.persistence.order.persistence;

import org.junit.jupiter.api.Test;
import vescame.orderstatuses.entity.order.Order;
import vescame.orderstatuses.persistence.order.OrderEntity;
import vescame.orderstatuses.persistence.storage.map.HashMapLongRawStorage;
import vescame.orderstatuses.usecases.order.calculator.OrderTotalAmountCalculator;
import vescame.orderstatuses.usecases.order.persistence.OrderLineRepository;

import java.math.BigDecimal;
import java.util.Collections;

import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static vescame.orderstatuses.entity.order.OrderStatus.CONFIRMED;
import static vescame.orderstatuses.entity.order.OrderStatus.PLACED;

class OrderInMemoryRepositoryTest {

    private final HashMapLongRawStorage<OrderEntity> storage = mock(HashMapLongRawStorage.class);
    private final OrderTotalAmountCalculator orderTotalAmountCalculator = mock(OrderTotalAmountCalculator.class);
    private final OrderLineRepository orderLineRepository = mock(OrderLineRepository.class);
    private final OrderInMemoryRepository repository = new OrderInMemoryRepository(
            storage,
            orderTotalAmountCalculator,
            orderLineRepository
    );

    @Test
    public void shouldGetOrder() {
        var customerId = 10L;
        var orderId = 1L;
        var entity = new OrderEntity(
                orderId,
                customerId,
                Collections.emptyList(),
                PLACED,
                now()
        );

        var expected = new Order(
                orderId,
                customerId,
                Collections.emptyList(),
                BigDecimal.ZERO,
                PLACED,
                entity.getCreateDate(),
                entity.getUpdateDate()
        );

        when(orderTotalAmountCalculator.calculateTotal(any())).thenReturn(BigDecimal.ZERO);

        when(storage.getById(orderId)).thenReturn(entity);

        var actual = repository.getOrderById(orderId);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowWhenTryingToGetAnNonExistentOrder() {
        var invalidOrderId = 3L;

        when(storage.getById(invalidOrderId)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> repository.getOrderById(invalidOrderId));
    }

    @Test
    public void shouldUpdateOrder() {
        var customerId = 14L;
        var orderId = 3L;
        var createDate = now();

        var entity = new OrderEntity(
                orderId,
                customerId,
                Collections.emptyList(),
                PLACED,
                createDate
        );

        var expected = new Order(
                orderId,
                customerId,
                Collections.emptyList(),
                BigDecimal.ZERO,
                PLACED,
                entity.getCreateDate(),
                entity.getUpdateDate()
        );

        when(orderTotalAmountCalculator.calculateTotal(any())).thenReturn(BigDecimal.ZERO);

        when(storage.getById(orderId)).thenReturn(entity).thenReturn(entity);

        repository.updateOrderStatus(orderId, CONFIRMED);

        var actual = repository.getOrderById(orderId);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowWhenTryingToUpdateAnNonExistentOrder() {
        var orderId = 3L;

        when(storage.getById(orderId)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> repository.updateOrderStatus(orderId, CONFIRMED));
    }
}
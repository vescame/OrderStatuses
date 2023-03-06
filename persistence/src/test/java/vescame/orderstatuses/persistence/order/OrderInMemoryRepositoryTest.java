package vescame.orderstatuses.persistence.order;

import org.junit.jupiter.api.Test;
import vescame.orderstatuses.persistence.storage.map.HashMapLongRawStorage;
import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static vescame.orderstatuses.entity.order.OrderStatus.CONFIRMED;
import static vescame.orderstatuses.entity.order.OrderStatus.PLACED;

class OrderInMemoryRepositoryTest {

    private final HashMapLongRawStorage<OrderEntity> storage = mock(HashMapLongRawStorage.class);
    private final OrderInMemoryRepository repository = new OrderInMemoryRepository(storage);


    @Test
    public void shouldGetOrder() {
        var orderId = 1L;
        var entity = new OrderEntity(
                orderId,
                PLACED,
                now()
        );

        when(storage.getById(orderId)).thenReturn(entity);

        var actual = repository.getOrderById(orderId);

        assertEquals(entity, actual);
    }

    @Test
    public void shouldThrowWhenTryingToGetAnNonExistentOrder() {
        var invalidOrderId = 3L;

        when(storage.getById(invalidOrderId)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> repository.getOrderById(invalidOrderId));
    }

    @Test
    public void shouldUpdateOrder() {
        var orderId = 3L;
        var createDate = now();
        var expected = new OrderEntity(
                orderId,
                CONFIRMED,
                createDate
        );
        var entity = new OrderEntity(
                orderId,
                PLACED,
                createDate
        );

        when(storage.getById(orderId)).thenReturn(entity).thenReturn(expected);

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
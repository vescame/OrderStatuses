package vescame.orderstatuses.usecases.service.Impl;

import org.junit.jupiter.api.Test;
import vescame.orderstatuses.entity.item.Item;
import vescame.orderstatuses.entity.order.Order;
import vescame.orderstatuses.entity.order.OrderLine;
import vescame.orderstatuses.entity.order.OrderStatus;
import vescame.orderstatuses.usecases.item.ItemValidator;
import vescame.orderstatuses.usecases.item.exception.InvalidItemException;
import vescame.orderstatuses.usecases.notification.Notifier;
import vescame.orderstatuses.usecases.order.OrderValidator;
import vescame.orderstatuses.usecases.order.exception.InvalidOrderException;
import vescame.orderstatuses.usecases.order.persistence.OrderRepository;
import java.math.BigDecimal;
import java.util.List;
import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static vescame.orderstatuses.entity.order.OrderStatus.PLACED;

class OrderServiceImplTest {

    private final ItemValidator itemValidator = mock(ItemValidator.class);
    private final OrderValidator orderValidator = mock(OrderValidator.class);
    private final OrderRepository repository = mock(OrderRepository.class);
    private final Notifier notifier = mock(Notifier.class);
    private final OrderServiceImpl service = new OrderServiceImpl(repository, orderValidator, itemValidator, notifier);

    @Test
    public void shouldUpdateOrderStatus() {
        Long orderId = 12L;
        OrderStatus status = PLACED;

        when(orderValidator.isOrderValid(orderId)).thenReturn(true);

        service.updateStatus(orderId, status);

        verify(orderValidator, times(1)).isOrderValid(orderId);
        verify(repository, times(1)).updateOrderStatus(orderId, status);
        verify(notifier, times(1)).batchNotify(orderId, PLACED);
    }

    @Test
    public void shouldThrowInvalidOrderWhenOrderIdNotFound() {
        Long orderId = 1998L;
        OrderStatus status = PLACED;

        when(orderValidator.isOrderValid(orderId)).thenReturn(false);

        assertThrows(InvalidOrderException.class, () -> service.updateStatus(orderId, status));

        verify(orderValidator, times(1)).isOrderValid(orderId);
        verify(repository, never()).updateOrderStatus(orderId, status);
    }

    @Test
    public void shouldCreateOrder() {
        var customerId = 42L;
        var itemId = 1L;
        var orderId = 1L;
        var orderLines = List.of(new OrderLine(itemId, 1));
        var order = new Order(customerId, orderLines, PLACED);

        var expected = new Order(orderId, customerId, orderLines, PLACED);

        when(itemValidator.isItemValid(itemId)).thenReturn(true);
        when(repository.createOrder(order)).thenReturn(expected);

        var actual = assertDoesNotThrow(() -> service.createNewOrder(customerId, orderLines));

        assertEquals(orderId, actual);

        verify(itemValidator, times(1)).isItemValid(itemId);
        verify(repository, times(1)).createOrder(order);
    }

    @Test
    public void shouldThrowInvalidItemWhenItemIdNotFound() {
        var customerId = 42L;
        var itemId = 19L;
        var orderLines = List.of(new OrderLine(itemId, 1));

        when(itemValidator.isItemValid(itemId)).thenReturn(false);

        assertThrows(InvalidItemException.class, () -> service.createNewOrder(customerId, orderLines));

        verify(itemValidator, times(1)).isItemValid(itemId);
        verify(repository, never()).createOrder(any());
    }

    @Test
    public void shouldGetOrderById() {
        var orderId = 1L;
        var customerId = 42L;
        var itemId = 12L;
        var item = new Item(itemId, "item name", "item details", BigDecimal.TEN);
        var orderLines = List.of(new OrderLine(1L, item, 1));

        var expected = new Order(orderId, customerId, orderLines, BigDecimal.TEN, PLACED, now(), now());

        when(orderValidator.isOrderValid(orderId)).thenReturn(true);
        when(repository.getOrderById(orderId)).thenReturn(expected);

        var actual = assertDoesNotThrow(() -> service.getOrderById(orderId));

        assertEquals(expected, actual);

        verify(orderValidator, times(1)).isOrderValid(orderId);
        verify(repository, times(1)).getOrderById(orderId);
    }
}

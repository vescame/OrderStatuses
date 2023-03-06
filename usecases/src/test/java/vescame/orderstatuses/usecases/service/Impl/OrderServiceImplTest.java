package vescame.orderstatuses.usecases.service.Impl;

import org.junit.jupiter.api.Test;
import vescame.orderstatuses.entity.order.OrderStatus;
import vescame.orderstatuses.usecases.order.OrderValidator;
import vescame.orderstatuses.usecases.order.exception.InvalidOrderException;
import vescame.orderstatuses.usecases.order.persistence.OrderRepository;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static vescame.orderstatuses.entity.order.OrderStatus.PLACED;

class OrderServiceImplTest {

    private final OrderValidator validator = mock(OrderValidator.class);
    private final OrderRepository repository = mock(OrderRepository.class);
    private final OrderServiceImpl service = new OrderServiceImpl(repository, validator);

    @Test
    public void shouldUpdateOrderStatus() {
        Long orderId = 12L;
        OrderStatus status = PLACED;

        when(validator.isOrderValid(orderId)).thenReturn(true);

        service.updateStatus(orderId, status);

        verify(validator, times(1)).isOrderValid(orderId);
        verify(repository, times(1)).updateOrderStatus(orderId, status);
    }

    @Test
    public void shouldThrowInvalidOrderWhenOrderIdIsNotFound() {
        Long orderId = 1998L;
        OrderStatus status = PLACED;

        when(validator.isOrderValid(orderId)).thenReturn(false);

        assertThrows(InvalidOrderException.class, () -> service.updateStatus(orderId, status));

        verify(validator, times(1)).isOrderValid(orderId);
        verify(repository, never()).updateOrderStatus(orderId, status);
    }
}

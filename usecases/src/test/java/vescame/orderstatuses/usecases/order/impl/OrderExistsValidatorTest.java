package vescame.orderstatuses.usecases.order.impl;

import org.junit.jupiter.api.Test;
import vescame.orderstatuses.usecases.order.persistence.OrderRepository;
import vescame.orderstatuses.usecases.validation.IdValidator;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderExistsValidatorTest {

    private final IdValidator idValidator = mock(IdValidator.class);
    private final OrderRepository repository = mock(OrderRepository.class);
    private final OrderExistsValidator orderValidator = new OrderExistsValidator(repository, idValidator);

    @Test
    public void shouldThrowWhenInvalidOrderId() {
        var orderId = 0L;

        when(idValidator.isValidId(orderId)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> orderValidator.isOrderValid(orderId));

        verify(idValidator, times(1)).isValidId(orderId);
        verify(repository, never()).existsOrderById(orderId);
    }


    @Test
    public void shouldReturnFalseWhenOrderIdNotFound() {
        var orderId = 12L;

        when(idValidator.isValidId(orderId)).thenReturn(true);
        when(repository.existsOrderById(orderId)).thenReturn(true);

        assertTrue(orderValidator.isOrderValid(orderId));

        verify(idValidator, times(1)).isValidId(orderId);
        verify(repository, times(1)).existsOrderById(orderId);
    }
}
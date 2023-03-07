package vescame.orderstatuses.httpapi.controller;

import org.junit.jupiter.api.Test;
import vescame.orderstatuses.entity.order.Order;
import vescame.orderstatuses.entity.order.OrderStatus;
import vescame.orderstatuses.httpapi.order.request.UpdateOrderStatusRequest;
import vescame.orderstatuses.httpapi.order.request.CreateOrderLineRequest;
import vescame.orderstatuses.httpapi.order.request.CreateOrderRequest;
import vescame.orderstatuses.httpapi.order.response.OrderResponse;
import vescame.orderstatuses.usecases.service.OrderService;
import java.math.BigDecimal;
import java.util.List;
import static java.time.LocalDateTime.now;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderControllerTest {

    private final OrderService orderService = mock(OrderService.class);
    private final OrderController orderController = new OrderController(orderService);

    @Test
    public void updateStatusShouldNotThrow() {
        var status = new UpdateOrderStatusRequest(OrderStatus.CONFIRMED);
        assertDoesNotThrow(() -> orderController.updateOrderStatus(1L, status));
    }

    @Test
    public void createOrder() {
        var orderRequest = new CreateOrderRequest(
                1L,
                List.of(
                        new CreateOrderLineRequest(1L, 4),
                        new CreateOrderLineRequest(10L, 1)
                )
        );

        assertDoesNotThrow(() -> orderController.createOrder(orderRequest));
    }

    @Test
    public void getOrderById() {
        var orderId = 1L;
        var customerId = 12L;

        var order = new Order(
                orderId,
                customerId,
                emptyList(),
                BigDecimal.ONE,
                OrderStatus.PLACED,
                now(),
                now()
        );

        var expected = new OrderResponse(
                orderId,
                customerId,
                emptyList(),
                OrderStatus.PLACED,
                BigDecimal.ONE
        );

        when(orderService.getOrderById(orderId)).thenReturn(order);

        var actual = assertDoesNotThrow(() -> orderController.getOrderById(orderId));

        assertEquals(expected, actual);
    }
}
package vescame.orderstatuses.httpapi.controller;

import org.junit.jupiter.api.Test;
import vescame.orderstatuses.entity.order.OrderStatus;
import vescame.orderstatuses.httpapi.order.request.UpdateOrderStatusRequest;
import vescame.orderstatuses.httpapi.order.request.CreateOrderLineRequest;
import vescame.orderstatuses.httpapi.order.request.CreateOrderRequest;
import vescame.orderstatuses.usecases.service.OrderService;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;

class OrderControllerTest {

    private final OrderService orderService = mock(OrderService.class);
    private final OrderController orderController = new OrderController(orderService);

    @Test
    public void updateStatusShouldNotThrow() {
        var status = new UpdateOrderStatusRequest(OrderStatus.CONFIRMED);
        assertDoesNotThrow(() -> orderController.updateOrderStatus(1L, status));
    }

}
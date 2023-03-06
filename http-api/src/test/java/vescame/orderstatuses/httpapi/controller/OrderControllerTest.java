package vescame.orderstatuses.httpapi.controller;

import org.junit.jupiter.api.Test;
import vescame.orderstatuses.entity.order.OrderStatus;
import vescame.orderstatuses.httpapi.order.UpdateOrderStatusRequest;
import vescame.orderstatuses.usecases.service.OrderService;
import static org.mockito.Mockito.mock;

class OrderControllerTest {

    private final OrderService orderService = mock(OrderService.class);
    private final OrderController orderController = new OrderController(orderService);

    @Test
    public void updateStatusShouldNotThrow() {
        var status = new UpdateOrderStatusRequest(OrderStatus.CONFIRMED);
        orderController.updateOrderStatus(1L, status);
    }

}
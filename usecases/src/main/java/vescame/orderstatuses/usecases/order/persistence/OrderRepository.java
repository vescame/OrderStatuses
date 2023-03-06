package vescame.orderstatuses.usecases.order.persistence;

import vescame.orderstatuses.entity.order.Order;
import vescame.orderstatuses.entity.order.OrderStatus;

public interface OrderRepository {

    Order getOrderById(Long orderId);

    void updateOrderStatus(Long orderId, OrderStatus status);
    boolean existsOrderById(Long orderId);
    Order createOrder(Order order);
}

package vescame.orderstatuses.usecases.order.persistence;

import vescame.orderstatuses.entity.order.Order;
import vescame.orderstatuses.entity.order.OrderStatus;

public interface OrderRepository {

    void updateOrderStatus(Long orderId, OrderStatus status);
    boolean existsOrderById(Long orderId);
    void createOrder(Order order);
}

package vescame.orderstatuses.usecases.service;

import vescame.orderstatuses.entity.order.Order;
import vescame.orderstatuses.entity.order.OrderLine;
import vescame.orderstatuses.entity.order.OrderStatus;

import java.util.Collection;

public interface OrderService {

    void updateStatus(Long orderId, OrderStatus orderStatus);

    Long createNewOrder(Long customerId, Collection<OrderLine> orderLines);

    Order getOrderById(Long orderId);
}

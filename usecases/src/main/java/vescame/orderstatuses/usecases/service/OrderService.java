package vescame.orderstatuses.usecases.service;

import vescame.orderstatuses.entity.order.OrderLine;
import vescame.orderstatuses.entity.order.OrderStatus;

import java.util.Collection;

public interface OrderService {

    void updateStatus(Long orderId, OrderStatus orderStatus);

    void createNewOrder(Long customerId, Collection<OrderLine> orderLines);
}

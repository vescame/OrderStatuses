package vescame.orderstatuses.usecases.service;

import vescame.orderstatuses.entity.order.Order;
import vescame.orderstatuses.entity.order.OrderLine;
import vescame.orderstatuses.entity.order.OrderStatus;
import vescame.orderstatuses.usecases.item.exception.InvalidItemException;
import vescame.orderstatuses.usecases.order.exception.InvalidOrderException;

import java.util.Collection;

public interface OrderService {

    void updateStatus(Long orderId, OrderStatus orderStatus) throws InvalidOrderException;

    Long createNewOrder(Long customerId, Collection<OrderLine> orderLines) throws InvalidItemException;

    Order getOrderById(Long orderId) throws InvalidOrderException;
}

package vescame.orderstatuses.usecases.service;

import vescame.orderstatuses.entity.order.OrderStatus;

public interface OrderService {

    void updateStatus(Long orderId, OrderStatus orderStatus);
}

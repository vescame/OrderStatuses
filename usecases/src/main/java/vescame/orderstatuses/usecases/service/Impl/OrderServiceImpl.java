package vescame.orderstatuses.usecases.service.Impl;

import org.springframework.stereotype.Service;
import vescame.orderstatuses.entity.order.OrderStatus;
import vescame.orderstatuses.usecases.order.persistence.OrderRepository;
import vescame.orderstatuses.usecases.order.OrderValidator;
import vescame.orderstatuses.usecases.order.exception.InvalidOrderException;
import vescame.orderstatuses.usecases.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;

    public OrderServiceImpl(OrderRepository orderRepository, OrderValidator orderValidator) {
        this.orderRepository = orderRepository;
        this.orderValidator = orderValidator;
    }

    @Override
    public void updateStatus(Long orderId, OrderStatus orderStatus) {
        if (!orderValidator.isOrderValid(orderId)) {
            throw new InvalidOrderException(orderId);
        }

       orderRepository.updateOrderStatus(orderId, orderStatus);
    }
}

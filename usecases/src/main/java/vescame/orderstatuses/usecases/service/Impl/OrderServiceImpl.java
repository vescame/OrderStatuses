package vescame.orderstatuses.usecases.service.Impl;

import org.springframework.stereotype.Service;
import vescame.orderstatuses.entity.order.Order;
import vescame.orderstatuses.entity.order.OrderLine;
import vescame.orderstatuses.entity.order.OrderStatus;
import vescame.orderstatuses.usecases.item.ItemValidator;
import vescame.orderstatuses.usecases.item.exception.InvalidItemException;
import vescame.orderstatuses.usecases.order.persistence.OrderRepository;
import vescame.orderstatuses.usecases.order.OrderValidator;
import vescame.orderstatuses.usecases.order.exception.InvalidOrderException;
import vescame.orderstatuses.usecases.service.OrderService;
import java.util.Collection;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;
    private final ItemValidator itemValidator;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            OrderValidator orderValidator,
            ItemValidator itemValidator
    ) {
        this.orderRepository = orderRepository;
        this.orderValidator = orderValidator;
        this.itemValidator = itemValidator;
    }

    @Override
    public void updateStatus(Long orderId, OrderStatus orderStatus) {
        if (!orderValidator.isOrderValid(orderId)) {
            throw new InvalidOrderException(orderId);
        }

       orderRepository.updateOrderStatus(orderId, orderStatus);
    }

    @Override
    public void createNewOrder(Long customerId, Collection<OrderLine> orderLines) {
        for (OrderLine orderLine : orderLines) {
            Long itemId = orderLine.item().id();
            if (!itemValidator.isItemValid(itemId)) {
                throw new InvalidItemException(itemId);
            }
        }

        orderRepository.createOrder(new Order(customerId, orderLines));
    }
}

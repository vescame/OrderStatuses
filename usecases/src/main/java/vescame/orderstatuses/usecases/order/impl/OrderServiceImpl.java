package vescame.orderstatuses.usecases.order.impl;

import org.springframework.stereotype.Service;
import vescame.orderstatuses.entity.order.Order;
import vescame.orderstatuses.entity.order.OrderLine;
import vescame.orderstatuses.entity.order.OrderStatus;
import vescame.orderstatuses.usecases.item.ItemValidator;
import vescame.orderstatuses.usecases.item.exception.InvalidItemException;
import vescame.orderstatuses.usecases.notification.Notifier;
import vescame.orderstatuses.usecases.order.persistence.OrderRepository;
import vescame.orderstatuses.usecases.order.OrderValidator;
import vescame.orderstatuses.usecases.order.exception.InvalidOrderException;
import vescame.orderstatuses.usecases.order.OrderService;
import java.util.Collection;
import static vescame.orderstatuses.entity.order.OrderStatus.PLACED;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;
    private final ItemValidator itemValidator;
    private final Notifier notifier;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            OrderValidator orderValidator,
            ItemValidator itemValidator,
            Notifier notifier
    ) {
        this.orderRepository = orderRepository;
        this.orderValidator = orderValidator;
        this.itemValidator = itemValidator;
        this.notifier = notifier;
    }

    @Override
    public void updateStatus(Long orderId, OrderStatus orderStatus) throws InvalidOrderException {
        if (!orderValidator.isOrderValid(orderId)) {
            throw new InvalidOrderException(orderId);
        }

        orderRepository.updateOrderStatus(orderId, orderStatus);

        notifier.batchNotify(orderId, orderStatus);
    }

    @Override
    public Long createNewOrder(Long customerId, Collection<OrderLine> orderLines) throws InvalidItemException {
        for (OrderLine orderLine : orderLines) {
            Long itemId = orderLine.item().id();
            if (!itemValidator.isItemValid(itemId)) {
                throw new InvalidItemException(itemId);
            }
        }

        return orderRepository.createOrder(new Order(customerId, orderLines, PLACED)).id();
    }

    @Override
    public Order getOrderById(Long orderId) throws InvalidOrderException {
        if (!orderValidator.isOrderValid(orderId)) {
            throw new InvalidOrderException(orderId);
        }

        return orderRepository.getOrderById(orderId);
    }
}

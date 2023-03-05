package vescame.orderstatuses.usecases.order.impl;

import org.springframework.stereotype.Component;
import vescame.orderstatuses.usecases.order.persistence.OrderRepository;
import vescame.orderstatuses.usecases.order.OrderValidator;
import vescame.orderstatuses.usecases.validation.IdValidator;

@Component
public class OrderExistsValidator implements OrderValidator {

    private final OrderRepository orderRepository;
    private final IdValidator idValidator;

    public OrderExistsValidator(OrderRepository orderRepository, IdValidator idValidator) {
        this.orderRepository = orderRepository;
        this.idValidator = idValidator;
    }

    @Override
    public boolean isOrderValid(Long orderId) {
        if (!idValidator.isValidId(orderId)) throw new IllegalArgumentException("an orderId cannot be < 1");

        return orderExists(orderId);
    }

    private boolean orderExists(Long orderId) {
        return orderRepository.existsOrderById(orderId);
    }
}

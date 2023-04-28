package vescame.orderstatuses.inmemory.order.persistence;

import org.springframework.stereotype.Repository;
import vescame.orderstatuses.entity.order.Order;
import vescame.orderstatuses.entity.order.OrderLine;
import vescame.orderstatuses.entity.order.OrderStatus;
import vescame.orderstatuses.persistence.PersistenceRepository;
import vescame.orderstatuses.persistence.order.OrderEntity;
import vescame.orderstatuses.usecases.order.calculator.OrderTotalAmountCalculator;
import vescame.orderstatuses.usecases.order.persistence.OrderLineRepository;
import vescame.orderstatuses.usecases.order.persistence.OrderRepository;
import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public class OrderInMemoryRepository implements OrderRepository {

    private final PersistenceRepository<Long, OrderEntity> STORAGE;
    private final OrderTotalAmountCalculator orderTotalAmountCalculator;
    private final OrderLineRepository orderLineRepository;

    public OrderInMemoryRepository(
            PersistenceRepository<Long, OrderEntity> persistenceRepository,
            OrderTotalAmountCalculator orderTotalAmountCalculator,
            OrderLineRepository orderLineRepository
    ) {
        this.STORAGE = persistenceRepository;
        this.orderLineRepository = orderLineRepository;
        this.orderTotalAmountCalculator = orderTotalAmountCalculator;
    }

    @Override
    public Order getOrderById(Long orderId) {
        OrderEntity entity = getEntityById(orderId);
        return convertOrder(entity);
    }

    private OrderEntity getEntityById(Long orderId) {
        OrderEntity entity = STORAGE.getById(orderId);
        if (entity == null) {
            throw new IllegalArgumentException(String.format("order (%d) doesn't exist", orderId));
        } else {
            return entity;
        }
    }

    @Override
    public void updateOrderStatus(Long orderId, OrderStatus status) {
        OrderEntity entity = getEntityById(orderId);
        var updatedEntity = new OrderEntity(
                entity.getId(),
                entity.getCustomerId(),
                entity.getOrderLines(),
                status,
                entity.getCreateDate()
        );
        STORAGE.update(updatedEntity);
    }

    @Override
    public Order createOrder(Order order) {
        final Collection<OrderLine> orderLines = orderLineRepository.createOrderLines(order.orderLines());
        OrderEntity entity = new OrderEntity(
                order.customerId(),
                orderLines,
                order.status(),
                LocalDateTime.now()
        );

        OrderEntity createdOrder = STORAGE.add(entity);

        return convertOrder(createdOrder);
    }

    @Override
    public boolean existsOrderById(Long orderId) {
        return STORAGE.getById(orderId) != null;
    }

    private Order convertOrder(OrderEntity entity) {
        return new Order(
                entity.getId(),
                entity.getCustomerId(),
                entity.getOrderLines(),
                orderTotalAmountCalculator.calculateTotal(entity.getOrderLines()),
                entity.getStatus(),
                entity.getCreateDate(),
                entity.getUpdateDate()
        );
    }
}

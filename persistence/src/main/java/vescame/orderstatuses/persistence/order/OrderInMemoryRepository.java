package vescame.orderstatuses.persistence.order;

import org.springframework.stereotype.Repository;
import vescame.orderstatuses.entity.order.OrderStatus;
import vescame.orderstatuses.persistence.storage.map.HashMapLongRawStorage;
import vescame.orderstatuses.usecases.order.persistence.OrderRepository;

@Repository
public class OrderInMemoryRepository implements OrderRepository {

    private final HashMapLongRawStorage<OrderEntity> STORAGE;

    public OrderInMemoryRepository(HashMapLongRawStorage<OrderEntity> hashMapRawPersistence) {
        this.STORAGE = hashMapRawPersistence;
    }

    public OrderEntity getOrderById(Long orderId) {
        OrderEntity entity = STORAGE.getById(orderId);
        if (entity == null) {
            throw new IllegalArgumentException(String.format("order (%d) doesn't exist", orderId));
        } else {
            return entity;
        }
    }

    @Override
    public void updateOrderStatus(Long orderId, OrderStatus status) {
        OrderEntity entity = getOrderById(orderId);
        var updatedEntity = new OrderEntity(
                entity.getId(),
                status,
                entity.getCreateDate()
        );
        STORAGE.update(updatedEntity);
    }

    @Override
    public boolean existsOrderById(Long orderId) {
        return getOrderById(orderId) != null;
    }
}

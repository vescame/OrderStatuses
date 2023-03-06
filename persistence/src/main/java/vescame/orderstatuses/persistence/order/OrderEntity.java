package vescame.orderstatuses.persistence.order;

import vescame.orderstatuses.entity.order.OrderStatus;
import vescame.orderstatuses.persistence.storage.map.LongPersistableEntity;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

public class OrderEntity implements LongPersistableEntity {

    private Long id;
    private Long customerId;
    private Collection<Long> orderLineIds;
    private final OrderStatus status;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    public OrderEntity(Long id, OrderStatus status, LocalDateTime createDate) {
        this.id = id;
        this.customerId = null;
        this.orderLineIds = Collections.emptyList();
        this.status = status;
        this.createDate = createDate;
        this.updateDate = LocalDateTime.now();
    }

    public OrderEntity(Long id, Long customerId, OrderStatus status, LocalDateTime createDate) {
        this.id = id;
        this.customerId = customerId;
        this.orderLineIds = Collections.emptyList();
        this.status = status;
        this.createDate = createDate;
        this.updateDate = LocalDateTime.now();
    }

    public OrderEntity(
            Long id,
            Long customerId,
            Collection<Long> orderLineIds,
            OrderStatus status,
            LocalDateTime createDate
    ) {
        this.id = id;
        this.customerId = customerId;
        this.orderLineIds = orderLineIds;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = LocalDateTime.now();
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomer(Long customerId) {
        this.customerId = customerId;
    }

    public Collection<Long> getOrderLines() {
        return orderLineIds;
    }

    public void setOrderLines(Collection<Long> orderLineIds) {
        this.orderLineIds = orderLineIds;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }
}

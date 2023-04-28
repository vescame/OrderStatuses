package vescame.orderstatuses.persistence.order;

import vescame.orderstatuses.entity.order.OrderLine;
import vescame.orderstatuses.entity.order.OrderStatus;
import vescame.orderstatuses.persistence.PersistableEntity;

import java.time.LocalDateTime;
import java.util.Collection;

public class OrderEntity implements PersistableEntity<Long> {

    private Long id;
    private Long customerId;
    private Collection<OrderLine> orderLines;
    private final OrderStatus status;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    public OrderEntity(Long id, Long customerId, Collection<OrderLine> orderLines, OrderStatus status, LocalDateTime createDate) {
        this.id = id;
        this.customerId = customerId;
        this.orderLines = orderLines;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = LocalDateTime.now();
    }

    public OrderEntity(
            Long customerId,
            Collection<OrderLine> orderLines,
            OrderStatus status,
            LocalDateTime createDate
    ) {
        this.id = null;
        this.customerId = customerId;
        this.orderLines = orderLines;
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

    public Collection<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(Collection<OrderLine> orderLines) {
        this.orderLines = orderLines;
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

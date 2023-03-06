package vescame.orderstatuses.usecases.notification;

import vescame.orderstatuses.entity.order.OrderStatus;

public interface Notifier {

    void batchNotify(Long orderId, OrderStatus orderStatus);
}

package vescame.orderstatuses.usecases.notification;

import vescame.orderstatuses.entity.order.OrderStatus;

import javax.naming.OperationNotSupportedException;

public interface Notifiable {

    void notifyCustomer(Long orderId, OrderStatus orderStatus) throws OperationNotSupportedException;
}

package vescame.orderstatuses.integration.notification;

import org.springframework.stereotype.Component;
import vescame.orderstatuses.entity.order.OrderStatus;
import vescame.orderstatuses.usecases.notification.Notifiable;

@Component
public class ConsoleMessageNotifiable implements Notifiable {

    @Override
    public void notifyCustomer(Long orderId, OrderStatus orderStatus) {
        System.out.printf(
                "Dear customer, your order (%d) has changed it's status to %s%n",
                orderId,
                orderStatus
        );
    }
}

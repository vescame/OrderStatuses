package vescame.orderstatuses.integration.notification;

import org.springframework.stereotype.Component;
import vescame.orderstatuses.entity.order.OrderStatus;
import vescame.orderstatuses.usecases.notification.Notifiable;
import vescame.orderstatuses.usecases.notification.Notifier;
import javax.naming.OperationNotSupportedException;
import java.util.Collection;

@Component
public class CustomerNotifier implements Notifier {

    private final Collection<Notifiable> notifiables;

    public CustomerNotifier(Collection<Notifiable> notifiables) {
        this.notifiables = notifiables;
    }

    @Override
    public void batchNotify(Long orderId, OrderStatus orderStatus) {
        for (Notifiable notifiable : notifiables) {
            try {
                notifiable.notifyCustomer(orderId, orderStatus);
            } catch (OperationNotSupportedException exception) {
                System.out.printf("Notification in %s not supported%n", notifiable.getClass().getSimpleName());
            }
        }
    }
}

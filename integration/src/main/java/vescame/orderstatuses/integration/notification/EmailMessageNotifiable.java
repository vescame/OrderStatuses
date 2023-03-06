package vescame.orderstatuses.integration.notification;

import org.springframework.stereotype.Component;
import vescame.orderstatuses.entity.order.OrderStatus;
import vescame.orderstatuses.usecases.notification.Notifiable;

import javax.naming.OperationNotSupportedException;

@Component
public class EmailMessageNotifiable implements Notifiable {

    @Override
    public void notifyCustomer(
            Long orderId,
            OrderStatus orderStatus
    ) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("cannot send e-mails yet");
    }
}

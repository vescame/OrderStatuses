package vescame.orderstatuses.integration.notification;

import org.junit.jupiter.api.Test;
import vescame.orderstatuses.usecases.notification.Notifiable;
import java.util.Collection;
import java.util.List;
import static vescame.orderstatuses.entity.order.OrderStatus.PLACED;

class CustomerNotifierTest {

    private final Collection<Notifiable> notifiables = List.of(
            new ConsoleMessageNotifiable(),
            new EmailMessageNotifiable()
    );
    private final CustomerNotifier notifier = new CustomerNotifier(notifiables);

    @Test
    public void shouldNotifyWithMultipleNotifiables() {
        notifier.batchNotify(1L, PLACED);
    }
}
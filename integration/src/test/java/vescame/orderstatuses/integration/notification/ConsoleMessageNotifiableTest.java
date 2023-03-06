package vescame.orderstatuses.integration.notification;

import org.junit.jupiter.api.Test;
import static vescame.orderstatuses.entity.order.OrderStatus.PLACED;

class ConsoleMessageNotifiableTest {

    private final ConsoleMessageNotifiable notifiable = new ConsoleMessageNotifiable();

    @Test
    public void shouldNotify() {
        notifiable.notifyCustomer(1L, PLACED);
    }
}
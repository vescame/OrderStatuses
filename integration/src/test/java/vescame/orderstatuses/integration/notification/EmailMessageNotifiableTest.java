package vescame.orderstatuses.integration.notification;

import org.junit.jupiter.api.Test;
import javax.naming.OperationNotSupportedException;
import static org.junit.jupiter.api.Assertions.*;
import static vescame.orderstatuses.entity.order.OrderStatus.PLACED;

class EmailMessageNotifiableTest {

    private final EmailMessageNotifiable notifiable = new EmailMessageNotifiable();

    @Test
    public void shouldThrowNotSupportedOperation() {
        assertThrows(OperationNotSupportedException.class, () -> notifiable.notifyCustomer(1L, PLACED));
    }

}
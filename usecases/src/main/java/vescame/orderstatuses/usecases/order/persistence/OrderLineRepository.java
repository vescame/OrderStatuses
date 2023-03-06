package vescame.orderstatuses.usecases.order.persistence;

import vescame.orderstatuses.entity.order.OrderLine;

import java.util.Collection;
import java.util.List;

public interface OrderLineRepository {

    List<OrderLine> createOrderLines(Collection<OrderLine> orderLine);
}

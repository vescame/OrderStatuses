package vescame.orderstatuses.persistence.order.persistence;

import org.springframework.stereotype.Repository;
import vescame.orderstatuses.entity.item.Item;
import vescame.orderstatuses.entity.order.OrderLine;
import vescame.orderstatuses.persistence.order.OrderLineEntity;
import vescame.orderstatuses.persistence.storage.map.HashMapLongRawStorage;
import vescame.orderstatuses.usecases.item.persistence.ItemRepository;
import vescame.orderstatuses.usecases.order.persistence.OrderLineRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class OrderLineInMemoryRepository implements OrderLineRepository {

    private final HashMapLongRawStorage<OrderLineEntity> STORAGE;
    private final ItemRepository itemRepository;

    public OrderLineInMemoryRepository(
            HashMapLongRawStorage<OrderLineEntity> hashMapLongRawStorage,
            ItemRepository itemRepository
    ) {
        this.STORAGE = hashMapLongRawStorage;
        this.itemRepository = itemRepository;
    }

    @Override
    public List<OrderLine> createOrderLines(Collection<OrderLine> orderLines) {
        final Collection<OrderLineEntity> newOrderLines = new ArrayList<>();
        for (OrderLine orderLine : orderLines) {
            Item item = itemRepository.getItemById(orderLine.item().id());
            OrderLineEntity entity = new OrderLineEntity(
                    item,
                    orderLine.quantity()
            );

            newOrderLines.add(STORAGE.add(entity));
        }

        return newOrderLines
                .stream()
                .map(orderLine -> new OrderLine(
                        orderLine.getId(),
                        orderLine.getItem(),
                        orderLine.getQuantity()
                )).toList();
    }
}

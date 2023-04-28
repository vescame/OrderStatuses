package vescame.orderstatuses.inmemory.order.persistence;

import org.junit.jupiter.api.Test;
import vescame.orderstatuses.entity.item.Item;
import vescame.orderstatuses.entity.order.OrderLine;
import vescame.orderstatuses.inmemory.item.persistence.ItemInMemoryRepository;
import vescame.orderstatuses.inmemory.hashmap.HashMapLongRepository;
import vescame.orderstatuses.persistence.PersistenceRepository;
import vescame.orderstatuses.persistence.order.OrderLineEntity;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
class OrderLineInMemoryRepositoryTest {

    private final PersistenceRepository<Long, OrderLineEntity> storage = mock(HashMapLongRepository.class);
    private final ItemInMemoryRepository itemRepository = mock(ItemInMemoryRepository.class);
    private final OrderLineInMemoryRepository repository = new OrderLineInMemoryRepository(storage, itemRepository);

    @Test
    public void shouldCreateMultipleOrderLines() {
        var orderLine = new OrderLine(1L, 2);
        var orderLines = List.of(orderLine);
        var item = new Item(1L, "item name", "item details", BigDecimal.TEN);

        var entity = new OrderLineEntity(
                1L,
                item,
                2
        );

        var expected = List.of(
                new OrderLine(
                        1L,
                        item,
                        2
                )
        );

        when(itemRepository.getItemById(any())).thenReturn(item);
        when(storage.add(any())).thenReturn(entity);

        var actual = repository.createOrderLines(orderLines);

        assertEquals(expected, actual);

        verify(itemRepository, times(1)).getItemById(any());
        verify(storage, times(1)).add(any());
    }
}
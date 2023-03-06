package vescame.orderstatuses.persistence.order.persistence;

import org.junit.jupiter.api.Test;
import vescame.orderstatuses.entity.item.Item;
import vescame.orderstatuses.entity.order.OrderLine;
import vescame.orderstatuses.persistence.item.persistence.ItemInMemoryRepository;
import vescame.orderstatuses.persistence.order.OrderLineEntity;
import vescame.orderstatuses.persistence.storage.map.HashMapLongRawStorage;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderLineInMemoryRepositoryTest {

    private final HashMapLongRawStorage<OrderLineEntity> storage = mock(HashMapLongRawStorage.class);
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
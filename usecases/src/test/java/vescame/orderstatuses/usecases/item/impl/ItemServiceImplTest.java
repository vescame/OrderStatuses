package vescame.orderstatuses.usecases.item.impl;


import org.junit.jupiter.api.Test;
import vescame.orderstatuses.entity.item.Item;
import vescame.orderstatuses.usecases.item.persistence.ItemRepository;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ItemServiceImplTest {

    private final ItemRepository repository = mock(ItemRepository.class);
    private final ItemServiceImpl itemService = new ItemServiceImpl(repository);

    @Test
    public void shouldGetItems() {
        var items = List.of(
                new Item(
                        1L,
                        "item name",
                        "item details",
                        BigDecimal.TEN
                )
        );

        when(repository.listAll()).thenReturn(items);

        var actual = itemService.getAll();

        assertEquals(items, actual);

        verify(repository, times(1)).listAll();
    }
}
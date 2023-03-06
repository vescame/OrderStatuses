package vescame.orderstatuses.httpapi.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import vescame.orderstatuses.entity.item.Item;
import vescame.orderstatuses.httpapi.item.ItemResponse;
import vescame.orderstatuses.usecases.service.ItemService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ItemControllerTest {

    private final ItemService service = Mockito.mock(ItemService.class);
    private final ItemController itemController = new ItemController(service);

    @Test
    public void shouldGetAllItems() {
        var items = List.of(
                new Item(
                        1L,
                        "item name",
                        "item details",
                        BigDecimal.TEN
                )
        );

        var expected = List.of(
                new ItemResponse(
                        1L,
                        "item name",
                        "item details",
                        BigDecimal.TEN
                )
        );

        when(service.getAll()).thenReturn(items);

        var actual = itemController.getAll();

        assertEquals(expected, actual);

        verify(service, times(1)).getAll();
    }

}
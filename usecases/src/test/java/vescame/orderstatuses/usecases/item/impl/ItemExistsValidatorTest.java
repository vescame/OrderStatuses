package vescame.orderstatuses.usecases.item.impl;

import org.junit.jupiter.api.Test;
import vescame.orderstatuses.usecases.item.persistence.ItemRepository;
import vescame.orderstatuses.usecases.validation.IdValidator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ItemExistsValidatorTest {

    private final IdValidator idValidator = mock(IdValidator.class);
    private final ItemRepository repository = mock(ItemRepository.class);
    private final ItemExistsValidator itemValidator = new ItemExistsValidator(repository, idValidator);

    @Test
    public void shouldThrowWhenInvalidOrderId() {
        var itemId = 0L;

        when(idValidator.isValidId(itemId)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> itemValidator.isItemValid(itemId));

        verify(idValidator, times(1)).isValidId(itemId);
        verify(repository, never()).existsItemById(itemId);
    }


    @Test
    public void shouldReturnFalseWhenOrderIdNotFound() {
        var itemId = 1018L;

        when(idValidator.isValidId(itemId)).thenReturn(true);
        when(repository.existsItemById(itemId)).thenReturn(true);

        assertTrue(itemValidator.isItemValid(itemId));

        verify(idValidator, times(1)).isValidId(itemId);
        verify(repository, times(1)).existsItemById(itemId);
    }
}
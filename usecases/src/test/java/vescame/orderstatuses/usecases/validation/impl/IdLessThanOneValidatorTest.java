package vescame.orderstatuses.usecases.validation.impl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IdLessThanOneValidatorTest {

    private final IdLessThanOneValidator validator = new IdLessThanOneValidator();

    @Test
    public void shouldReturnFalseWhenIdIsLessThanOne() {
        assertFalse(validator.isValidId(-10L));
    }

    @Test
    public void shouldReturnFalseWhenIdIsZero() {
        assertFalse(validator.isValidId(0L));
    }

    @Test
    public void shouldReturnTrueWhenIdIsOne() {
        assertTrue(validator.isValidId(1L));
    }

    @Test
    public void shouldReturnTrueWhenIdIsMoreThanOne() {
        assertTrue(validator.isValidId(1987L));
    }
}

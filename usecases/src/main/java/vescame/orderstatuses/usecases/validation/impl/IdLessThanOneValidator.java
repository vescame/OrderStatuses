package vescame.orderstatuses.usecases.validation.impl;

import org.springframework.stereotype.Component;
import vescame.orderstatuses.usecases.validation.IdValidator;

@Component
public class IdLessThanOneValidator implements IdValidator {

    @Override
    public boolean isValidId(Long id) {
        return !isIdLessThanOne(id);
    }

    private boolean isIdLessThanOne(Long id) {
        return id < 1;
    }
}

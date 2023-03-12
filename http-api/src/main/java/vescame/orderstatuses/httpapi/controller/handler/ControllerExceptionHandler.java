package vescame.orderstatuses.httpapi.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vescame.orderstatuses.usecases.common.exception.NotFoundException;
import vescame.orderstatuses.usecases.item.exception.InvalidItemException;
import vescame.orderstatuses.usecases.order.exception.InvalidOrderException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(InvalidOrderException.class)
    public ResponseEntity<ErrorResponse> handleInvalidOrder(InvalidOrderException ex) {
        return handleNotFound(ex);
    }

    @ExceptionHandler(InvalidItemException.class)
    public ResponseEntity<ErrorResponse> handleInvalidItem(InvalidItemException ex) {
        return handleNotFound(ex);
    }

    private ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {
        return new ResponseEntity<>(ErrorResponse.create(ex, NOT_FOUND, ex.getMessage()), NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequest(MethodArgumentNotValidException ex) {
        Collection<FieldValidationError> invalidFields = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            FieldError fieldError = (FieldError) error;
            invalidFields.add(new FieldValidationError(fieldError.getField(), error.getDefaultMessage()));
        }

        ErrorResponse errorResponse = ErrorResponse
                .builder(ex, BAD_REQUEST, "Request body validation failed!")
                .detailMessageArguments(Map.of("fields", invalidFields))
                .build();

        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }
}

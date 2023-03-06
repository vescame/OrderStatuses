package vescame.orderstatuses.httpapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import vescame.orderstatuses.entity.order.OrderLine;
import vescame.orderstatuses.httpapi.order.request.UpdateOrderStatusRequest;
import vescame.orderstatuses.httpapi.order.request.CreateOrderRequest;
import vescame.orderstatuses.usecases.order.exception.InvalidOrderException;
import vescame.orderstatuses.usecases.service.OrderService;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        orderService.createNewOrder(
                createOrderRequest.customerId(),
                createOrderRequest.orderLines()
                        .stream()
                        .map(orderLine -> new OrderLine(orderLine.itemId()))
                        .toList()
        );
    }

    @PutMapping("/{orderId}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrderStatus(@PathVariable Long orderId, @RequestBody UpdateOrderStatusRequest orderStatus) {
        orderService.updateStatus(orderId, orderStatus.status());
    }

    @ExceptionHandler(InvalidOrderException.class)
    public ResponseEntity<ErrorResponse> handleInvalidAccountId(InvalidOrderException ex, WebRequest web) {
        return new ResponseEntity<>(ErrorResponse.create(ex, NOT_FOUND, ex.getMessage()), NOT_FOUND);
    }
}
